package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.domain.repositories.ObjectiveRepository;

import javax.ejb.Stateful;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Stateful
public class JPAIterationRepository extends AbstractJPARepository<IterationEntity> implements
		IterationRepository {
	
	@Override
	public Optional<IterationEntity> findCurrentIteration() {
		TypedQuery<IterationEntity> query = this.em.createNamedQuery("IterationEntity.findCurrentIteration", IterationEntity.class);
		query.setParameter("now", LocalDate.now());
		return this.getOptionalSingleResult(query);
	}
	
	@Override
	public Optional<IterationEntity> findByName(String name) {
		TypedQuery<IterationEntity> query = this.em.createNamedQuery("IterationEntity.findByName", IterationEntity.class);
		query.setParameter("name", name);
		return this.getOptionalSingleResult(query);
	}
	
	@Override
	protected Class<IterationEntity> getClazz() {
		return IterationEntity.class;
	}
}
