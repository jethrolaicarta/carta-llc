package com.carta.llc.core.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import com.google.gson.Gson;

@Entity
public class OwnableAsset {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String type;
  private String lastName;

  protected OwnableAsset() {}

  public OwnableAsset(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
}