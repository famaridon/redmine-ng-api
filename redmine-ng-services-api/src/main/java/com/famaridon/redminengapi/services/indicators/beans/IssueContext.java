package com.famaridon.redminengapi.services.indicators.beans;

public class IssueContext extends  AbstractBean {

  private IssueScope scope;
  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public IssueScope getScope() {
    return scope;
  }

  public void setScope(IssueScope scope) {
    this.scope = scope;
  }
}
