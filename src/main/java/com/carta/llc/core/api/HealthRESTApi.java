package com.carta.llc.core.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carta.llc.core.data.dto.HealthCheckResult;
import com.carta.llc.core.service.HealthCheckService;

/**
 * health check endpoint. Any custom health check logic can be added in this
 * class Spring boot actuactor and management have default healthcheck endpoint.
 * This class should only be used for custom health check implementation
 *
 */
@RestController
public class HealthRESTApi {
	private static final Logger logger = LoggerFactory.getLogger(HealthRESTApi.class);

	@Autowired
	private HealthCheckService healthCheckService;

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
	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public HealthCheckResult healthFromObject() {
		return healthCheckService.healthCheck();
	}

	/**
	 * This example demonstrates how to create custom response on a low level
	 * http-aware way.
	 *
	 * @return
	 */
	@RequestMapping(value = "/health/alternative", method = RequestMethod.GET)
	public ResponseEntity<String> health() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		return new ResponseEntity<>(healthCheckService.healthCheckReturnJson().toString(), headers, HttpStatus.OK);

	}

	/**
	 * handle any unexpected exception
	 * 
	 * @param e any unexpected exception
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleError(RuntimeException e) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		logger.error(e.getMessage(), e);
		return new ResponseEntity<>(String.format("{\"error\":\"%s\"}", e.getMessage()), headers,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
