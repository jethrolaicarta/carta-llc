package com.carta.llc.core.service;

import com.carta.llc.core.data.model.Entitlement;

import java.util.Map;

public interface EntitlementService {

	Entitlement get(final String id);

	Entitlement create(final Entitlement entity);

	Map<String, Double> getCapTable(String companyId);
	
}
