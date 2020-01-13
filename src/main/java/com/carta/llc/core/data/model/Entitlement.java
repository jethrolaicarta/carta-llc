package com.carta.llc.core.data.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Entitlement {

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

}