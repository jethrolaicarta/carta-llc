package com.carta.llc.core.data.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public enum EntitlementType implements Serializable{

	MIU("Membership Unit"), PIU("Profit Unit");

	private final String description;

	private EntitlementType(final String description) {
		this.description = description;
	}

}