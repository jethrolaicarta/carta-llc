package com.carta.llc.core.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carta.llc.core.api.validator.EntitlementRequestValidator;
import com.carta.llc.core.data.dao.EntitlementDao;
import com.carta.llc.core.data.model.Entitlement;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * @author jlai
 */
//@RestController
//@RequestMapping("/api/entitlement")
public class EntitlementController {
	private static final Logger logger = LoggerFactory.getLogger(EntitlementController.class);
	private static final String ENTITLEMENT_NAME = "ENTITLEMENT";

//	@Autowired
//	@Qualifier("entitlementDao")
	private EntitlementDao entitlementDao;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> get(@PathVariable("id") String id)

	{
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		Entitlement entity = entitlementDao.get(id);

		if (entity != null) {
			return new ResponseEntity<>(new Gson().toJson(entity), headers, HttpStatus.OK);
		}
		return new ResponseEntity<>(String.format("{\"error\":\"%s with id %s not found\"}", ENTITLEMENT_NAME, id),
				headers, HttpStatus.NOT_FOUND);

	}

	// delegate the empty body validation in app by setting the body required=false
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody(required = false) String requestBody)

	{
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		Entitlement parsedRequest = EntitlementRequestValidator.validateAndParsePostRequest(requestBody);

		Entitlement entitlement = entitlementDao.get(parsedRequest.getId());

		// TODO
		return new ResponseEntity<>(new Gson().toJson(entitlement), headers, HttpStatus.OK);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleError(IllegalArgumentException e) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
		JsonObject responseBody = new JsonObject();
		responseBody.addProperty("result", "failed");
		responseBody.addProperty("error", e.getMessage());
		logger.error(e.getMessage(), e);
		return new ResponseEntity<>(responseBody.toString(), headers, HttpStatus.BAD_REQUEST);
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
		JsonObject responseBody = new JsonObject();
		responseBody.addProperty("result", "failed");
		responseBody.addProperty("error", e.getMessage());
		logger.error(e.getMessage(), e);
		return new ResponseEntity<>(responseBody.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}