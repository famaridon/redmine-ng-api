package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.domain.repositories.ObjectiveRepository;

import javax.ejb.Stateful;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.Optional;

@Stateful
public class JPAIterationRepository extends AbstractJPARepository<IterationEntity> implements
		IterationRepository {
	
	@Override
	public Optional<IterationEntity> findCurrentIteration() {
		TypedQuery<IterationEntity> query = this.em.createNamedQuery("getCurrentIteration", IterationEntity.class);
		query.setParameter("now", new Date());
		return Optional.of(query.getSingleResult());
	}
	
	@Override
	protected Class<IterationEntity> getClazz() {
		return IterationEntity.class;
	}
}
