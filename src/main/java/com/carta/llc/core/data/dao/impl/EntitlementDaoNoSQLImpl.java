package com.carta.llc.core.data.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carta.llc.core.data.dao.EntitlementDao;
import com.carta.llc.core.data.model.Entitlement;

import java.util.List;

/**
 * @author jlai
 */
public class EntitlementDaoNoSQLImpl implements EntitlementDao<Entitlement> {

	private static final String ENTITLEMENT_TABLE_NAME = "entitlement";
	final private Logger logger = LoggerFactory.getLogger(EntitlementDaoNoSQLImpl.class);

	public Entitlement get(final String id) {
		return null;
	}

	@Override
	public Entitlement create(Entitlement entity) {
		return null;
	}

	@Override
	public Entitlement update(Entitlement entity) {

		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Entitlement upsert(Entitlement entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Entitlement> getByCompanyId(String companyId) {
		return null;
	}

}
