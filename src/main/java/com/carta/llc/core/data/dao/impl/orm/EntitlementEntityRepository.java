package com.carta.llc.core.data.dao.impl.orm;

import com.carta.llc.core.data.model.EntitlementEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntitlementEntityRepository extends CrudRepository<EntitlementEntity, String> {
    @Query("SELECT model FROM EntitlementEntity model WHERE model.companyId =:companyId")
    public List<EntitlementEntity> getByCompanyId(@Param("companyId") String companyId);
}
