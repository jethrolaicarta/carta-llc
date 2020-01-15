package com.carta.llc.core.api.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carta.llc.core.Constants;
import com.carta.llc.core.data.dto.HealthCheckResponse;
import com.carta.llc.core.data.model.Entitlement;
import com.carta.llc.core.service.EntitlementService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * health check endpoint. Any custom health check logic can be added in this
 * class Spring boot actuactor and management have default healthcheck endpoint.
 * This class should only be used for custom health check implementation
 *
 */
@RestController
public class HealthController {
	private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

	@Autowired
	@Qualifier("entitlementService")
	private EntitlementService entitlementService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> healthRoot() {
		return health();
	}

	/**
	 * This example demonstrates how to simplify response creation in a
	 * http-agnostic way
	 * 
	 * @return
	 */
	@RequestMapping(value = "/health/object", method = RequestMethod.GET)
	public HealthCheckResponse healthFromObject() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		return HealthCheckResponse.create("OK", "OK", new HashMap<>());
	}
	
	
	/**
	 * This example demonstrates how to create custom response on a low level
	 * http-aware way.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public ResponseEntity<String> health() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		Entitlement seedEntity = entitlementService.get(Constants.SEED_ENTITLEMENT_ID);

		JsonObject responseBody = new JsonObject();
		JsonObject statusBody = new JsonObject();

		statusBody.addProperty("server", "OK");
		statusBody.addProperty("db", seedEntity == null ? "DOWN" : "OK");
		statusBody.add("dependencies", new JsonArray());
		responseBody.add("status", statusBody);

		return new ResponseEntity<>(responseBody.toString(), headers, HttpStatus.OK);
	}
}
