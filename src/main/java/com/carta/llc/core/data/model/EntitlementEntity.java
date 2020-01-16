package com.carta.llc.core.data.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="entitlement")
public class EntitlementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private Date created;
	private Date updated;
	private Date deleted;
//	private String type;
	private String holderId;
	private String companyId;
	private Double quantity;
}