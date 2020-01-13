package com.carta.llc.core.data.model;

public enum EntitlementType {

	MIU("Membership Unit"), PIU("Profit Unit");

	private final String description;

	private EntitlementType(final String description) {
		this.description = description;
	}

}