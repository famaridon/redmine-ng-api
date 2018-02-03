package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.PriorityEntity;
import com.famaridon.redminengapi.domain.repositories.PriorityRepository;

import javax.ejb.Stateful;

@Stateful
public class DefaultPriorityRepository extends AbstractExternalEntityRepository<PriorityEntity> implements PriorityRepository {
	
	@Override
	protected Class<PriorityEntity> getClazz() {
		return PriorityEntity.class;
	}
}
