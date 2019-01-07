package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.AbstractEntity;

import java.util.Optional;

public interface Repository<T extends AbstractEntity> {
	
	public T save(T entity);
	public Optional<T> findById(Long id);
	public void delete(T entity);
	public Iterable<T> findAll();
	public Iterable<T> findAll(Long offset, Long limit);

}
