package com.carta.llc.core.service;

import com.carta.llc.core.data.model.Entitlement;

public interface EntitlementService {

	Entitlement get(final String id);

	Entitlement create(final Entitlement entity);
	
}
