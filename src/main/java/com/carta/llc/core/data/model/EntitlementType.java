package com.carta.llc.core.data.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntitlementType {

	private String id;
	private String typeId;
	private String holderId;
	private Double quantityValue;
	private String quantityUnit;
	private String status;
	private String terminationNote;
	private String originEntitlementId;
	private String vestingPolicyId;
	private String vestingScheduleId;
	private Double cost;
	private String approvalStatusId;
	private String complianceId;
	private List<String> actionIds;

}