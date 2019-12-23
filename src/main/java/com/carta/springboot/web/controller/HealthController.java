package com.carta.springboot.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(value = "/")
	public ResponseEntity<String> healthRoot() {
		return health();
	}

	@RequestMapping(value = "/health")
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
}
