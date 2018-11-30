package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.domain.repositories.ObjectiveRepository;
import com.famaridon.redminengapi.domain.repositories.StatusRepository;

import javax.ejb.Stateful;

@Stateful
public class JPAObjectiveRepository extends AbstractJPARepository<ObjectiveEntity> implements ObjectiveRepository {
	
	@Override
	protected Class<ObjectiveEntity> getClazz() {
		return ObjectiveEntity.class;
	}
}
