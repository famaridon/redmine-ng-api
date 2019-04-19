package com.famaridon.redminengapi.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CategoryEntity extends AbstractEntity {

  @Column
  protected ScopeEntity scope;
  @Column
  protected ContextEntity context;
}
