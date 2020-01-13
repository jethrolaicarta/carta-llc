package com.carta.llc.core.data.model;

import com.carta.llc.core.data.LLCBaseData;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company implements LLCBaseData {

	private String id;
	private Long created;
	private Long updated;
	private Long deleted;
	private String name;

	/**
	 * identity in Carta Identity Service
	 */
	private String cartaId;

}