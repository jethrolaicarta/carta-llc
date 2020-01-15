package com.carta.llc.core.data.dao.impl;

import com.carta.llc.core.data.dao.EntitlementDao;
import com.carta.llc.core.data.model.Entitlement;
import com.carta.llc.core.data.model.EntitlementEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.List;

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
}