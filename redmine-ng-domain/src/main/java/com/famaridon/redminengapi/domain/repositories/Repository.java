package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.AbstractEntity;

import java.util.Optional;

public interface Repository<T extends AbstractEntity> {
	
	T save(T entity);
	Optional<T> findById(Long id);
	void delete(T entity);
	Iterable<T> findAll();
	Iterable<T> findAll(Long offset, Long limit);

}
