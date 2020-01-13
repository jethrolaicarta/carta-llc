package com.carta.llc.core.data.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntitlementHolder {

	private String id;	
	private String name;
	private String type;
	private String holderId;
}