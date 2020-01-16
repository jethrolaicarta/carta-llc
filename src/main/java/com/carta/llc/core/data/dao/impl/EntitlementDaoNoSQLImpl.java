package com.carta.llc.core.data.dao.impl;

import java.util.List;

import com.carta.llc.core.data.dao.EntitlementDao;
import com.carta.llc.core.data.model.Entitlement;

/**
 * @author jlai
 */
public class EntitlementDaoNoSQLImpl implements EntitlementDao<Entitlement> {

	public Entitlement get(final String id) {
		throw new UnsupportedOperationException(
				"NoSQL DAO implementation is only used for demonstrating the concept of Data abstraction. It's not implemented. ");
	}

	@Override
	public Entitlement create(Entitlement entity) {
		throw new UnsupportedOperationException(
				"NoSQL DAO implementation is only used for demonstrating the concept of Data abstraction. It's not implemented. ");
	}

	@Override
	public Entitlement update(Entitlement entity) {
		throw new UnsupportedOperationException(
				"NoSQL DAO implementation is only used for demonstrating the concept of Data abstraction. It's not implemented. ");
	}

	@Override
	public void delete(String id) {
		throw new UnsupportedOperationException(
				"NoSQL DAO implementation is only used for demonstrating the concept of Data abstraction. It's not implemented. ");
	}

	@Override
	public Entitlement upsert(Entitlement entity) {
		throw new UnsupportedOperationException(
				"NoSQL DAO implementation is only used for demonstrating the concept of Data abstraction. It's not implemented. ");
	}

	@Override
	public List<Entitlement> getByCompanyId(String companyId) {
		throw new UnsupportedOperationException(
				"NoSQL DAO implementation is only used for demonstrating the concept of Data abstraction. It's not implemented. ");
	}

}
