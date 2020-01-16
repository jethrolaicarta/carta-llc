package com.carta.llc.core.api.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.carta.llc.core.Application;
import com.carta.llc.core.api.grpc.EntitlementServiceGrpc;
import com.carta.llc.core.api.grpc.EntitlementServiceRequest;
import com.carta.llc.core.api.grpc.EntitlementServiceResponse;
import com.carta.llc.test.IntegrationTest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * 
 * @author jethrolai
 *
 */
@Category(IntegrationTest.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = Application.class)
public class EntitlementGrpcServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(EntitlementGrpcServiceTest.class);

	@Value("${grpc.port}")
	private int grpcPort;

	@Test
	public void testEntitlementGrpcService() {
		logger.info("Runnung Grpc Test ...");
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", grpcPort).usePlaintext().build();

		EntitlementServiceGrpc.EntitlementServiceBlockingStub stub = EntitlementServiceGrpc.newBlockingStub(channel);

		EntitlementServiceResponse response = stub
				.get(EntitlementServiceRequest.newBuilder().setEntitlementId("1").build());

		channel.shutdown();
		Assert.assertEquals(response.getEntitlementId(), "1");
	}
}