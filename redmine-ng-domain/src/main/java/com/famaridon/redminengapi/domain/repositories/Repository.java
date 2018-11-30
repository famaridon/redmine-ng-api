package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.AbstractEntity;

public interface Repository<T extends AbstractEntity> {
	
	public T save(T entity);
	public T findById(Long id);
	public Iterable<T> findAll();
	public Iterable<T> findAll(Long offset, Long limit);

}
