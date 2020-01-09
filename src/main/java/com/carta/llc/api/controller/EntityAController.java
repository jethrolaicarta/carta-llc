package com.carta.llc.api.controller;

import java.util.Date;
import java.util.List;

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

import com.carta.llc.api.validator.EntityARequestValidator;
import com.carta.llc.data.dao.EntityADao;
import com.carta.llc.data.model.EntityA;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * @author jlai
 */
@RestController
@RequestMapping("/entity/a")
public class EntityAController {
	private static final Logger logger = LoggerFactory.getLogger(EntityAController.class);

	@Autowired
	@Qualifier("entityADao")
	private EntityADao entityADao;

	@RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<String> get(@PathVariable("uuid") String uuid)

	{
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		EntityA entity = entityADao.findByUuid(uuid);

		if (entity != null) {
			return new ResponseEntity<>(new Gson().toJson(entity), headers, HttpStatus.OK);
		}
		return new ResponseEntity<>(String.format("{\"error\":\"entityA with uuid %s not found\"}", uuid), headers,
				HttpStatus.NOT_FOUND);

	}

	// delegate the empty body validation in app by setting the body required=false
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String> postEntityA(@RequestBody(required = false) String requestBody)

	{
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		EntityA parsedRequest = EntityARequestValidator.validateAndParsePostRequest(requestBody);

		EntityA entityA = entityADao.findByUuid(parsedRequest.getUuid());

		if (entityA != null) {
			entityA.setStatus(parsedRequest.getStatus());
			entityADao.upsert(entityA);
			entityA = entityADao.findByUuid(parsedRequest.getUuid());
			return new ResponseEntity<>(new Gson().toJson(entityA), headers, HttpStatus.OK);
		}

		// entityA not exist
		entityADao.upsert(parsedRequest.getUuid(), parsedRequest.getStatus(), new Date(), new JsonObject().toString());
		entityA = entityADao.findByUuid(parsedRequest.getUuid());
		return new ResponseEntity<>(new Gson().toJson(entityA), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<String> getAll()

	{
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		List<EntityA> entityAs = entityADao.getAll();
		return new ResponseEntity<>(new Gson().toJson(entityAs), headers, HttpStatus.OK);
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