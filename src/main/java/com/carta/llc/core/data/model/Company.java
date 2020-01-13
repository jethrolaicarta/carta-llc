package com.carta.llc.core.data.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {

	private String id;	
	private String name;
	
	/**
	 * identity in Carta Identity Service
	 */
	private String cartaId;
}