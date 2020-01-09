package com.carta.llc.data.dao;

import java.util.Date;
import java.util.List;

import com.carta.llc.data.model.EntityA;

/**
 * @author jlai
 */
public interface EntityADao {
	List<EntityA> getAll();

	EntityA findByUuid(final String uuid);

	void upsert(String uuid, String status, Date createdDate, String detail);

	void upsert(EntityA entityA);
}
