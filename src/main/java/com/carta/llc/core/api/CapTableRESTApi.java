package com.carta.llc.core.api;

import com.carta.llc.core.api.validator.EntitlementRequestValidator;
import com.carta.llc.core.data.model.Entitlement;
import com.carta.llc.core.service.EntitlementService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author jlai
 */
@RestController
@RequestMapping("/api/captable")
public class CapTableRESTApi {
	private static final Logger logger = LoggerFactory.getLogger(CapTableRESTApi.class);
	private static final String ENTITLEMENT_NAME = "ENTITLEMENT";

	@Autowired
	@Qualifier("entitlementService")
	private EntitlementService entitlementService;

	@RequestMapping(value = "/company/{companyId}", method = RequestMethod.GET)
	public ResponseEntity<String> get(@PathVariable("companyId") String companyId)

	{
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		//validation

		Map<String, Double> capTable = entitlementService.getCapTable(companyId);

		return new ResponseEntity<>(new Gson().toJson(capTable), headers, HttpStatus.OK);
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