package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.CategoryEntity;
import com.famaridon.redminengapi.domain.repositories.CategoryRepository;

public class JPACategoryRepository extends AbstractJPARepository<CategoryEntity> implements CategoryRepository {

  @Override
  protected Class<CategoryEntity> getClazz() {
    return CategoryEntity.class;
  }
}
