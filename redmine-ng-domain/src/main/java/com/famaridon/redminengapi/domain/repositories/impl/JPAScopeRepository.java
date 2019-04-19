package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.ScopeEntity;
import com.famaridon.redminengapi.domain.repositories.ScopeRepository;
import javax.ejb.Stateful;

@Stateful
public class JPAScopeRepository extends AbstractJPARepository<ScopeEntity> implements ScopeRepository {

  @Override
  protected Class<ScopeEntity> getClazz() {
    return ScopeEntity.class;
  }

}
