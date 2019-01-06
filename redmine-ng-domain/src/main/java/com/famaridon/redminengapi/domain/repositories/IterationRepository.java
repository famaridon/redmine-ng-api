package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.IterationEntity;

import java.util.Optional;

public interface IterationRepository extends Repository<IterationEntity> {
	Optional<IterationEntity> findCurrentIteration();
	Optional<IterationEntity> findByNumber(Long number);
}
