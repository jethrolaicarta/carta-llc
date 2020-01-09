package com.carta.llc.core.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import com.google.gson.Gson;

import lombok.Builder;
import lombok.Data;

/**
 * persistence layer object
 * @author jethrolai
 *
 */
@Data
@Builder
@Entity
public class AssetOwner {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String type;
  private String name;
  private String detail;

  
  protected AssetOwner() {}


  @Override
  public String toString() {
    return new Gson().toJson(this);
  }


public AssetOwner(Long id, String type, String name, String detail) {
	super();
	this.id = id;
	this.type = type;
	this.name = name;
	this.detail = detail;
}
}