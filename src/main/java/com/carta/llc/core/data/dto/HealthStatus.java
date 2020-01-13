package com.carta.llc.core.data.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthStatus implements Serializable {

	private static final long serialVersionUID = -3718268212379864975L;
	private String db;
	private String depenendencies;
	private String server;

}
