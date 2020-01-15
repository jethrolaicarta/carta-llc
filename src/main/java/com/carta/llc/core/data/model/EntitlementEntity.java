package com.carta.llc.core.data.model;

import com.carta.llc.core.data.LLCBaseData;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class EntitlementEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private String id;
	private Long created;
	private Long updated;
	private Long deleted;
	private String type;
	private String holderId;
	private String companyId;
	private Double quantity;
}