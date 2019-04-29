package com.famaridon.redminengapi.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@NamedQuery(name = "IssueContextEntity.findAllByScope", query = "select o from IssueContextEntity o where o.scope = :scope")
@Entity
public class IssueContextEntity extends IssueCategoryEntity {

  @ManyToOne
  private IssueScopeEntity scope;
  @Column
  private String description;

  public IssueScopeEntity getScope() {
    return scope;
  }

  public void setScope(IssueScopeEntity scope) {
    this.scope = scope;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
