package com.carta.llc.core.data;

import java.util.List;

/**
 * @author jlai
 */
public interface LlcBaseDao {
	List<LLCBaseData> getAll();

	LLCBaseData findById(final String id);

	void upsert(LLCBaseData entity);
}
