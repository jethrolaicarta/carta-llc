package com.carta.llc.core.data.model;

import com.carta.llc.core.data.LLCBaseData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 
 * @author jethrolai
 * @since Jan 17, 2020
 */
@Data
@Builder
public class NewModelClass implements LLCBaseData {

	private String id;
	private Long created;
	private Long updated;
	private Long deleted;
}
