package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.repositories.ObjectiveRepository;

import javax.ejb.Stateful;
import javax.persistence.TypedQuery;

@Stateful
public class JPAObjectiveRepository extends AbstractJPARepository<ObjectiveEntity> implements ObjectiveRepository {
	
	@Override
	protected Class<ObjectiveEntity> getClazz() {
		return ObjectiveEntity.class;
	}

	@Override
	public Iterable<ObjectiveEntity> findAllByIteration(IterationEntity iterationEntity, Long offset, Long limit) {
		TypedQuery<ObjectiveEntity> typedQuery = this.em.createNamedQuery("ObjectiveEntity.findAllByIteration",ObjectiveEntity.class);
		typedQuery.setParameter("iteration", iterationEntity);
		typedQuery.setFirstResult(Math.toIntExact(offset));
		typedQuery.setMaxResults(Math.toIntExact(limit));
		return typedQuery.getResultList();
	}
}
