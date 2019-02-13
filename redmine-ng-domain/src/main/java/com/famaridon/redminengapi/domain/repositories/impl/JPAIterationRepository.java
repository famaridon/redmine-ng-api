package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.BurndownChartEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import java.time.LocalDate;
import java.util.Optional;
import javax.ejb.Stateful;
import javax.persistence.TypedQuery;

@Stateful
public class JPAIterationRepository extends AbstractJPARepository<IterationEntity> implements
    IterationRepository {

  @Override
  public IterationEntity save(IterationEntity entity) {
    boolean isCreation = false;
    if (entity.getId() == null) {
      isCreation = true;
    }
    IterationEntity saved = super.save(entity);
    if (isCreation) {
      BurndownChartEntity burndownChartEntity = new BurndownChartEntity();
      burndownChartEntity.setIteration(saved);
      this.em.merge(burndownChartEntity);
    }
    return saved;
  }


  @Override
  public Optional<IterationEntity> findCurrentIteration() {
    TypedQuery<IterationEntity> query = this.em.createNamedQuery("IterationEntity.findCurrentIteration", IterationEntity.class);
    query.setParameter("now", LocalDate.now());
    return this.getOptionalSingleResult(query);
  }

  @Override
  public Optional<IterationEntity> findByNumber(Long number) {
    TypedQuery<IterationEntity> query = this.em.createNamedQuery("IterationEntity.findByNumber", IterationEntity.class);
    query.setParameter("number", number);
    return this.getOptionalSingleResult(query);
  }

  @Override
  protected Class<IterationEntity> getClazz() {
    return IterationEntity.class;
  }
}
