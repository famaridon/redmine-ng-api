package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.IterationEntity;

import java.util.Optional;

public interface IterationRepository extends Repository<IterationEntity> {
	public Optional<IterationEntity> findCurrentIteration();
}
