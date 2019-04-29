package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.IssueScopeEntity;
import com.famaridon.redminengapi.domain.repositories.IssueScopeRepository;
import javax.ejb.Stateful;

@Stateful
public class JPAScopeRepository extends AbstractJPARepository<IssueScopeEntity> implements IssueScopeRepository {

  @Override
  protected Class<IssueScopeEntity> getClazz() {
    return IssueScopeEntity.class;
  }

}
