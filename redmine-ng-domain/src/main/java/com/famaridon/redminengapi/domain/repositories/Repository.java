package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.AbstractEntity;

public interface Repository<T extends AbstractEntity> {
	
	public T findById(Long id);
	
}
