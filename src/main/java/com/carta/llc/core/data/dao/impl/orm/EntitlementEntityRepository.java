package com.carta.llc.core.data.dao.impl.orm;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.carta.llc.core.data.model.EntitlementEntity;

public interface EntitlementEntityRepository extends CrudRepository<EntitlementEntity, String> {
	@Query(value = "SELECT model FROM EntitlementEntity model WHERE model.companyId =:companyId")
	public List<EntitlementEntity> getByCompanyId(@Param("companyId") String companyId);
}
