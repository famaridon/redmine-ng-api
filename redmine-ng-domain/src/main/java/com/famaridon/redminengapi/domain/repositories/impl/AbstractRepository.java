package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.AbstractEntity;
import com.famaridon.redminengapi.domain.repositories.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractRepository<T extends AbstractEntity> implements Repository<T> {

	@PersistenceContext(name = "redmine-domain")
	protected EntityManager em;
	
	public T findById(Long id){
		return this.em.find(this.getClazz(), id);
	}
	
	public T save(T entity) {
		return this.em.merge(entity);
	}
	
	@Override
	public Iterable<T> findAll() {
		CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.getClazz());
		return this.em.createQuery(criteriaQuery).getResultList();
	}
	
	protected abstract Class<T> getClazz();
	
}
