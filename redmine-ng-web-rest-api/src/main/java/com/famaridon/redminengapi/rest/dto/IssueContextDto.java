package com.famaridon.redminengapi.rest.dto;

import com.famaridon.redminengapi.rest.refs.IssueScopeRef;

public class IssueContextDto extends AbstractDto{

  private IssueScopeRef scope;
  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public IssueScopeRef getScope() {
    return scope;
  }

  public void setScope(IssueScopeRef scope) {
    this.scope = scope;
  }
}
