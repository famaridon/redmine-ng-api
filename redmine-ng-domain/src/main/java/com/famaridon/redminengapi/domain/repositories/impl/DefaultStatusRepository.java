package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.domain.repositories.StatusRepository;

import javax.ejb.Stateful;

@Stateful
public class DefaultStatusRepository extends AbstractRepository<StatusEntity> implements StatusRepository {
	
	@Override
	protected Class<StatusEntity> getClazz() {
		return StatusEntity.class;
	}
}
