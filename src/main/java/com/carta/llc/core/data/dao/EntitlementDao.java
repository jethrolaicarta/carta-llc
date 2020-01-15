package com.carta.llc.core.data.dao;

import com.carta.llc.core.data.model.Entitlement;

import java.util.List;

public interface EntitlementDao<Enttilement> {
	
	Entitlement get(final String id);
	
	Entitlement create(final Entitlement entity);
	
	Entitlement update(final Entitlement entity);
	
	void delete(final String id);
	
	Entitlement upsert(final Entitlement entity);

	List<Entitlement> getByCompanyId(String companyId);
}
