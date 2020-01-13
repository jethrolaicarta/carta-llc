package com.carta.llc.core.data.model;

import com.carta.llc.core.data.LLCBaseData;

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

	public Entitlement(String id, Long created, Long updated, Long deleted, String type, String holderId,
			Double quantityValue, String quantityUnit, String status, String terminationNote,
			String originEntitlementId, String vestingPolicyId, String vestingScheduleId, Double cost,
			String approvalStatusId, String complianceId) {
		super();
		this.id = id;
		this.created = created;
		this.updated = updated;
		this.deleted = deleted;
		this.type = type;
		this.holderId = holderId;
		this.quantityValue = quantityValue;
		this.quantityUnit = quantityUnit;
		this.status = status;
		this.terminationNote = terminationNote;
		this.originEntitlementId = originEntitlementId;
		this.vestingPolicyId = vestingPolicyId;
		this.vestingScheduleId = vestingScheduleId;
		this.cost = cost;
		this.approvalStatusId = approvalStatusId;
		this.complianceId = complianceId;
	}

}