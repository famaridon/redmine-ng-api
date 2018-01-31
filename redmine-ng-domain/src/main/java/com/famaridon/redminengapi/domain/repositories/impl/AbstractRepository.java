package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.AbstractEntity;
import com.famaridon.redminengapi.domain.repositories.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepository<T extends AbstractEntity> implements Repository<T> {

	@PersistenceContext(name = "redmine-domain")
	protected EntityManager em;
	
	public T findById(Long id){
		return this.em.find(this.getClazz(), id);
	}
	
	protected abstract Class<T> getClazz();
	
}
