package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.AbstractEntity;

public interface ExternalEntityRepository<T extends AbstractEntity> extends Repository<T> {
	
	public T findByExternalId(Long externalId);
	
}
