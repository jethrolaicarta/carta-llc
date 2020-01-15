package com.carta.llc.core.api.validator;

import org.apache.commons.lang3.StringUtils;

import com.carta.llc.core.data.model.Entitlement;
import com.google.gson.Gson;

/**
 * @author jlai
 */
public class EntitlementRequestValidator {
	private static final String ID_FIELD_NAME = "id";

	/**
	 * private static final String TYPE_FIELD_NAME = "type";
	 * 
	 * private static Set<String> supportedTypes =
	 * Arrays.stream(EntitlementType.values()).map(type -> type.name())
	 * .collect(Collectors.toSet());
	 */

	private EntitlementRequestValidator() {

	}

	public static Entitlement validateAndParsePostRequest(final String requestBodyString) {

		if (StringUtils.isBlank(requestBodyString)) {
			throw new IllegalArgumentException("request body is blank. ");
		}
		Entitlement request = null;
		try {
			request = new Gson().fromJson(requestBodyString, Entitlement.class);
		} catch (Exception e) {
			throw new IllegalArgumentException(
					String.format("failed to parse the request body. Make sure your request is a valid json. body: %s",
							requestBodyString),
					e);
		}
		if (StringUtils.isBlank(request.getId())) {
			throw new IllegalArgumentException(String.format("missing required %s field.", ID_FIELD_NAME));
		}
		/**
		 * if (StringUtils.isBlank(request.getType())) { throw new
		 * IllegalArgumentException(String.format("missing required %s field.",
		 * TYPE_FIELD_NAME)); }
		 * 
		 * if (supportedTypes.contains(request.getType().toUpperCase()) == false) {
		 * throw new IllegalArgumentException( String.format("input type value %s is not
		 * supported.", request.getType())); }
		 */

		return request;
	}
}
