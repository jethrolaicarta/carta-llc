package com.carta.llc.core.data.dao.impl;

import com.carta.llc.core.data.dao.EntitlementDao;
import com.carta.llc.core.data.dao.impl.orm.EntitlementEntityRepository;
import com.carta.llc.core.data.model.Entitlement;
import com.carta.llc.core.data.model.EntitlementEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jlai
 */

public class EntitlementDaoORMImpl implements EntitlementDao {

    @Autowired
    private EntitlementEntityRepository entitlementEntityRepository;

    @Override
    public Entitlement get(String id) {
        return Entitlement.builder().id(entitlementEntityRepository.findById(id).get().getId()).build();
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

    }

    @Override
    public Entitlement upsert(Entitlement entity) {
        return null;
    }

    @Override
    public List<Entitlement> getByCompanyId(String companyId) {
        List<EntitlementEntity> entities = entitlementEntityRepository.getByCompanyId(companyId);
        return entities.stream()
                       .map(EntitlementDaoORMImpl::toEntitlement)
                       .collect(Collectors.toList());
    }

    private static Entitlement toEntitlement(EntitlementEntity entity) {
        return Entitlement.builder()
                          .id(entity.getId())
                          .companyId(entity.getCompanyId())
                          .holderId(entity.getHolderId())
                          .quantity(entity.getQuantity())
                          .build();
    }
}