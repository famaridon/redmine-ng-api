package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.IterationEntity;

public interface IterationRepository extends Repository<IterationEntity> {
	public IterationEntity findCurrentIteration();
}
