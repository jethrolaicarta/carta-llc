package com.carta.llc.core.data.model;

import com.carta.llc.core.data.LLCBaseData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Entitlement implements LLCBaseData {

	private String id;
	private Long created;
	private Long updated;
	private Long deleted;
	private String type;
	private String holderId;
	private Double quantity;

//	private String quantityUnit;
//	private String status;
//	private String terminationNote;
//	private String originEntitlementId;
//	private String vestingPolicyId;
//	private String vestingScheduleId;
//	private Double cost;
//	private String approvalStatusId;
//	private String complianceId;

}