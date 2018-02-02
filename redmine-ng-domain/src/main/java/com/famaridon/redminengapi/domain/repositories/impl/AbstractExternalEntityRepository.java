package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.AbstractExternalEntity;
import com.famaridon.redminengapi.domain.repositories.ExternalEntityRepository;
import com.famaridon.redminengapi.domain.repositories.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Optional;

public abstract class AbstractExternalEntityRepository<T extends AbstractExternalEntity> extends AbstractRepository<T> implements Repository<T>, ExternalEntityRepository<T> {

	@Override
	@Transactional
	public Optional<T> findByExternalId(Long externalId) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(this.getClazz());
		Root<T> c = q.from(this.getClazz());
		ParameterExpression<Long> p = cb.parameter(Long.class);
		q.select(c).where(cb.equal(c.get("externalId"), p));
		TypedQuery<T> query = this.em.createQuery(q);
		query.setParameter(p, externalId);
		T result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			// nothing to do
		}
		
		return Optional.ofNullable(result);
		
	}
	
	protected abstract Class<T> getClazz();
	
}
