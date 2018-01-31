package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.TrackerEntity;
import com.famaridon.redminengapi.domain.repositories.TrackerRepository;

import javax.ejb.Stateful;

@Stateful
public class DefaultTrackerRepository extends AbstractRepository<TrackerEntity> implements TrackerRepository {

	@Override
	protected Class<TrackerEntity> getClazz() {
		return TrackerEntity.class;
	}
	
}
