package com.carta.llc.api.validator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.carta.llc.data.model.EntityA;
import com.carta.llc.data.model.EntityAStatus;
import com.google.gson.Gson;

/**
 * @author jlai
 */
public class EntityARequestValidator {
	private static final String UUID_FIELD_NAME = "uuid";
	private static final String STATUS_FIELD_NAME = "status";

	private static Set<String> supportedStatus = Arrays.stream(EntityAStatus.values()).map(status -> status.name())
			.collect(Collectors.toSet());

	private EntityARequestValidator() {

	}

	public static EntityA validateAndParsePostRequest(final String requestBodyString) {

		if (StringUtils.isBlank(requestBodyString)) {
			throw new IllegalArgumentException("request body is blank. ");
		}
		EntityA request = null;
		try {
			request = new Gson().fromJson(requestBodyString, EntityA.class);
		} catch (Exception e) {
			throw new IllegalArgumentException(
					String.format("failed to parse the request body. Make sure your request is a valid json. body: %s",
							requestBodyString),
					e);
		}
		if (StringUtils.isBlank(request.getUuid())) {
			throw new IllegalArgumentException(String.format("missing required %s field.", UUID_FIELD_NAME));
		}

		if (StringUtils.isBlank(request.getStatus())) {
			throw new IllegalArgumentException(String.format("missing required %s field.", STATUS_FIELD_NAME));
		}

		if (supportedStatus.contains(request.getStatus().toUpperCase()) == false) {
			throw new IllegalArgumentException(
					String.format("input status value %s is not supported.", request.getStatus()));
		}

		return request;
	}
}
