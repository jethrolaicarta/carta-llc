package com.carta.llc.core.data.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.carta.llc.core.data.dao.EntitlementDao;
import com.carta.llc.core.data.dao.impl.orm.EntitlementEntityRepository;
import com.carta.llc.core.data.entity.EntitlementEntity;
import com.carta.llc.core.data.model.Entitlement;
import com.google.gson.Gson;

/**
 * @author jlai
 */

public class EntitlementDaoORMImpl implements EntitlementDao {

	private static final Logger logger = LoggerFactory.getLogger(EntitlementDaoORMImpl.class);

	@Autowired
	private EntitlementEntityRepository entitlementEntityRepository;

	@Override
	public Entitlement get(String id) {

		Optional<EntitlementEntity> entity = entitlementEntityRepository.findById(id);
		return Entitlement.builder().id(entity.get().getId()).build();
	}

	@Override
	public Entitlement create(Entitlement entity) {
		Gson gson = new Gson();
		EntitlementEntity e = (EntitlementEntity) gson.fromJson(gson.toJson(entity), EntitlementEntity.class);

		logger.info(new Gson().toJson(e));

		EntitlementEntity createdEntity = entitlementEntityRepository.save(e);

		return (Entitlement) gson.fromJson(gson.toJson(createdEntity), Entitlement.class);
	}

	@Override
	public Entitlement update(Entitlement entity) {
		return null;
	}

	@Override
	public void delete(String id) {

	}

	@Override
	public Entitlement upsert(Entitlement entity) {
		return null;
	}

	@Override
	public List<Entitlement> getByCompanyId(String companyId) {
		List<EntitlementEntity> entities = entitlementEntityRepository.getByCompanyId(companyId);
		return entities.stream().map(EntitlementDaoORMImpl::toEntitlement).collect(Collectors.toList());
	}

	private static Entitlement toEntitlement(EntitlementEntity entity) {
		return Entitlement.builder().id(entity.getId()).companyId(entity.getCompanyId()).holderId(entity.getHolderId())
				.quantity(entity.getQuantity()).build();
	}
}