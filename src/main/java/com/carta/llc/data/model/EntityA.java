package com.carta.llc.data.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class EntityA {

	private String uuid;
	private String status;
	private Date createdDate;
	private String detail;
	private Date updatedDate;

	public EntityA(String uuid, String status, Date createdDate, String detail, Date updatedDate) {
		super();
		this.uuid = uuid;
		this.status = status;
		this.createdDate = createdDate;
		this.detail = detail;
		this.updatedDate = updatedDate;
	}
}