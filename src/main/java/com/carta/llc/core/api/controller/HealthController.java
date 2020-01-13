package com.carta.llc.core.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carta.llc.core.data.dto.HealthCheckResponse;
import com.carta.llc.core.data.dto.HealthStatus;
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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> healthRoot() {
		return health();
	}

	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public ResponseEntity<String> health() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		JsonObject responseBody = new JsonObject();
		JsonObject statusBody = new JsonObject();

		statusBody.addProperty("server", "OK");
		statusBody.addProperty("db", "N/A");
		statusBody.add("dependencies", new JsonArray());
		responseBody.add("status", statusBody);

		return new ResponseEntity<>(responseBody.toString(), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/health/object", method = RequestMethod.GET)
	public HealthCheckResponse healthObject() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		HealthStatus status = new HealthStatus();
		status.setServer("OK");
		status.setDb("OK");
		status.setDepenendencies("OK");

		HealthCheckResponse response = new HealthCheckResponse(status);

		return response;
	}
}
