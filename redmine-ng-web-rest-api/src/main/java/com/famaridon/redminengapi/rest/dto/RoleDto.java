package com.famaridon.redminengapi.rest.dto;

public class RoleDto extends AbstractDto {

  private Boolean inherited;

  public Boolean getInherited() {
    return this.inherited;
  }

  public void setInherited(Boolean inherited) {
    this.inherited = inherited;
  }
}
