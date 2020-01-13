package com.carta.llc.core.data.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntitlementAction {

	private String id;
	private String definition;
}