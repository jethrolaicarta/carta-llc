package com.carta.llc.core.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.carta.llc.core.data.dao.EntitlementDao;
import com.carta.llc.core.data.model.Entitlement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntitlementServiceImpl implements EntitlementService {

    @Autowired
    @Qualifier("entitlementDao")
    private EntitlementDao entitlementDao;

    @Override
    public Entitlement get(String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException(
                    String.format("id is required for retrieving Entitlement. input id:{}", id));
        }
        return entitlementDao.get(id);
    }

    @Override
    public Entitlement create(Entitlement entity) {

        if (entity == null) {
            throw new IllegalArgumentException("Entitlement cannot be created with null input");
        }

        if (StringUtils.isBlank(entity.getId())) {
            throw new IllegalArgumentException(String.format(
                    "entitlement.id is required for creating Entitlement. input entitlement.id:{}", entity.getId()));
        }
        return entitlementDao.create(entity);
    }

    @Override
    public Map<String, Double> getCapTable(String companyId) {
//		return null;
        List<Entitlement> entitlements = entitlementDao.getByCompanyId(companyId);
        Map<String, Double> capTable = new HashMap();
        Double totalQuantity = 0d;
        for (Entitlement e : entitlements) {
            totalQuantity += e.getQuantity();
            capTable.put(e.getHolderId(), capTable.getOrDefault(e.getHolderId(), 0d) + e.getQuantity());
        }
        for (Entitlement e : entitlements) {
            capTable.put(e.getHolderId(), capTable.get(e.getHolderId()) / totalQuantity);
        }

        return capTable;
    }

}
