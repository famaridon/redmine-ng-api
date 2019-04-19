package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.ContextEntity;
import com.famaridon.redminengapi.domain.repositories.ContextRepository;

public class JPAContextRepository extends AbstractJPARepository<ContextEntity> implements ContextRepository {

  @Override
  protected Class<ContextEntity> getClazz() {
    return ContextEntity.class;
  }

}
