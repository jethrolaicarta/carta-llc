package com.carta.llc.core.data.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User implements LLCData {

	private String id;
	private String firstName;
	private String lastName;

	/**
	 * identity in Carta Identity Service
	 */
	private String cartaId;

}