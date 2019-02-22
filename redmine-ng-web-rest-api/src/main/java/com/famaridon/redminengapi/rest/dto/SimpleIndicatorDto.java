package com.famaridon.redminengapi.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleIndicatorDto {

  @JsonProperty("name")
  private String name;
  @JsonProperty("value")
  private Long value;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getValue() {
    return value;
  }

  public void setValue(Long value) {
    this.value = value;
  }
}
