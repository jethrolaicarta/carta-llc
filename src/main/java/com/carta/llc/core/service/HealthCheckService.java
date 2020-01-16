package com.carta.llc.core.service;

import java.net.URI;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.carta.llc.core.Constants;
import com.carta.llc.core.api.grpc.EntitlementServiceGrpc;
import com.carta.llc.core.api.grpc.EntitlementServiceRequest;
import com.carta.llc.core.api.grpc.EntitlementServiceResponse;
import com.carta.llc.core.data.dto.HealthCheckResult;
import com.carta.llc.core.data.model.Entitlement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * 
 * @author jethrolai
 *
 */
@Service
public class HealthCheckService {

	@Value("${grpc.port}")
	private int grpcServicePort;

	@Value("${server.port}")
	private int restServicePort;

	@Autowired
	private EntitlementService entitlementService;

	private RestTemplate restTemplate = new RestTemplate();

	public HealthCheckResult healthCheck() {
		Entitlement seedEntity = entitlementService.get(Constants.SEED_ENTITLEMENT_ID);
		boolean dbStatus = seedEntity != null && StringUtils.isNotBlank(seedEntity.getId())
				&& seedEntity.getId().equals(Constants.SEED_ENTITLEMENT_ID);

		return HealthCheckResult.create("OK", dbStatus ? "OK" : "ERROR", new HashMap<String, String>() {
			{
				put("rest", checkRestService() ? "OK" : "ERROR");
				put("grpc", checkGrpcService() ? "OK" : "ERROR");
			}
		}, new HashMap<>());

	}

	public JsonObject healthCheckReturnJson() {

		Entitlement seedEntity = entitlementService.get(Constants.SEED_ENTITLEMENT_ID);

		JsonObject responseBody = new JsonObject();
		JsonObject statusBody = new JsonObject();

		JsonArray services = new JsonArray();
		JsonObject grpcServiceStatus = new JsonObject();
		grpcServiceStatus.addProperty("grpc", checkGrpcService() ? "OK" : "ERROR");
		services.add(grpcServiceStatus);

		JsonObject restServiceStatus = new JsonObject();
		grpcServiceStatus.addProperty("rest", checkRestService() ? "OK" : "ERROR");
		services.add(restServiceStatus);

		statusBody.addProperty("server", "OK");
		statusBody.add("services", services);
		statusBody.addProperty("db", seedEntity == null ? "DOWN" : "OK");
		statusBody.add("dependencies", new JsonArray());
		responseBody.add("status", statusBody);

		return responseBody;
	}

	public String healthCheckReturnRawJson() {

		Entitlement seedEntity = entitlementService.get(Constants.SEED_ENTITLEMENT_ID);

		JsonObject responseBody = new JsonObject();
		JsonObject statusBody = new JsonObject();

		JsonArray services = new JsonArray();
		JsonObject grpcServiceStatus = new JsonObject();
		grpcServiceStatus.addProperty("grpc", checkGrpcService() ? "OK" : "ERROR");
		services.add(grpcServiceStatus);

		JsonObject restServiceStatus = new JsonObject();
		grpcServiceStatus.addProperty("rest", checkRestService() ? "OK" : "ERROR");
		services.add(restServiceStatus);

		statusBody.addProperty("server", "OK");
		statusBody.add("services", services);
		statusBody.addProperty("db", seedEntity == null ? "DOWN" : "OK");
		statusBody.add("dependencies", new JsonArray());
		responseBody.add("status", statusBody);

		return responseBody.toString();
	}

	public boolean checkGrpcService() {

		ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", grpcServicePort).usePlaintext().build();

		EntitlementServiceGrpc.EntitlementServiceBlockingStub stub = EntitlementServiceGrpc.newBlockingStub(channel);

		EntitlementServiceResponse response = stub
				.get(EntitlementServiceRequest.newBuilder().setEntitlementId(Constants.SEED_ENTITLEMENT_ID).build());

		channel.shutdown();
		return response != null && response.getEntitlementId() == Constants.SEED_ENTITLEMENT_ID;

	}

	public boolean checkRestService() {

		ResponseEntity<Entitlement> response = restTemplate.getForEntity(URI.create("localhost:" + restServicePort),
				Entitlement.class);
		return response.getBody() != null && response.getBody().getId() == Constants.SEED_ENTITLEMENT_ID;

	}

}
