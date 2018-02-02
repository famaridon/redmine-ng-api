package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.AbstractEntity;

import java.util.Optional;

public interface ExternalEntityRepository<T extends AbstractEntity> extends Repository<T> {
	
	public Optional<T> findByExternalId(Long externalId);
	
}
