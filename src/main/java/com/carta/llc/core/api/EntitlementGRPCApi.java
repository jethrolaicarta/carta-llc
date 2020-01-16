package com.carta.llc.core.api;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.carta.llc.core.api.grpc.EntitlementServiceGrpc.EntitlementServiceImplBase;
import com.carta.llc.core.api.grpc.EntitlementServiceRequest;
import com.carta.llc.core.api.grpc.EntitlementServiceResponse;
import com.carta.llc.core.data.model.Entitlement;
import com.carta.llc.core.service.EntitlementService;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

/**
 * 
 * @author jethrolai
 *
 */
@Component
public class EntitlementGRPCApi extends EntitlementServiceImplBase {
	private static final Logger logger = LoggerFactory.getLogger(EntitlementGRPCApi.class);

	@Autowired
	@Qualifier("entitlementService")
	private EntitlementService entitlementService;

	@Value("${grpc.port}")
	private int grpcPort;

	private ExecutorService executorService;
	private Server server;

	@Override
	public void get(EntitlementServiceRequest request, StreamObserver<EntitlementServiceResponse> responseObserver) {

		Entitlement entitlement = entitlementService.get(request.getEntitlementId());
		EntitlementServiceResponse response = EntitlementServiceResponse.newBuilder()
				.setEntitlementId(entitlement.getId()).build();

		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}

	@PostConstruct
	public void startGRPCService() {
		BasicThreadFactory factory = new BasicThreadFactory.Builder().namingPattern("grpc-server-thread-%d").build();

		server = ServerBuilder.forPort(grpcPort).addService(this).build();
		executorService = Executors.newSingleThreadExecutor(factory);
		executorService.execute(new Runnable() {

			@Override
			public void run() {
				logger.info("Starting Grpc Service on port {}", grpcPort);

				try {
					server.start();
					server.awaitTermination();
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Grpc service has stopped");
				}
			}
		});

		executorService.shutdown();

	}

	@PreDestroy
	public void beandestroy() {
		if (executorService != null) {
			server.shutdownNow();
			executorService.shutdown();
		}
	}

}
