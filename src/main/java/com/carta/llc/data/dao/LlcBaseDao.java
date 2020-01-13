package com.carta.llc.data.dao;

import java.util.Date;
import java.util.List;

import com.carta.llc.data.model.EntityA;

/**
 * @author jlai
 */
public interface LlcBaseDao {
	List<? extends LLCData> getAll();

	LLCData findById(final String id);

	void upsert(String uuid, String status, Date createdDate, String detail);

	void upsert(EntityA entityA);
}
