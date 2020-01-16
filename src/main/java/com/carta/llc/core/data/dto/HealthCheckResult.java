package com.carta.llc.core.data.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class HealthCheckResult implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6277792461505341780L;

	private HealthStatus status;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	static class HealthStatus implements Serializable {

		private static final long serialVersionUID = -3718268212379864975L;

		private String server;

		private String db;

		@Builder.Default
		private Map<String, String> services = new HashMap<String, String>();

		@Builder.Default
		private Map<String, String> depenendencies = new HashMap<String, String>();

	}

	public static HealthCheckResult create(final String serverStatus, final String dbStatus,
			final Map<String, String> servicesStatus, final Map<String, String> dependenciesStatus) {
		return new HealthCheckResult(new HealthStatus(serverStatus, dbStatus, servicesStatus, dependenciesStatus));
	}

}
