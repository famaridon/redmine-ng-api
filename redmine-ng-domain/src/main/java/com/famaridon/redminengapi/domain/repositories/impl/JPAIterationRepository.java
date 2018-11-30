package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.repositories.ObjectiveRepository;

import javax.ejb.Stateful;

@Stateful
public class JPAIterationRepository extends AbstractJPARepository<ObjectiveEntity> implements ObjectiveRepository {
	
	@Override
	protected Class<ObjectiveEntity> getClazz() {
		return ObjectiveEntity.class;
	}
}
