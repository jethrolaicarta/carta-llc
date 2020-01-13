package com.carta.llc.core.data.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class HealthCheckResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6277792461505341780L;
	private HealthStatus status;

}
