package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.BurndownChartEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.repositories.BurndownChartRepository;
import java.time.LocalDate;
import java.util.Optional;
import javax.ejb.Stateful;
import javax.persistence.TypedQuery;

@Stateful
public class JPABurndownChartRepository extends AbstractJPARepository<BurndownChartEntity> implements
    BurndownChartRepository {

  @Override
  protected Class<BurndownChartEntity> getClazz() {
    return BurndownChartEntity.class;
  }


  @Override
  public Optional<BurndownChartEntity> findByIteration(IterationEntity iterationEntity) {
    TypedQuery<BurndownChartEntity> query = this.em.createNamedQuery("BurndownChartEntity.findByIteration", BurndownChartEntity.class);
    query.setParameter("iteration", iterationEntity);
    return this.getOptionalSingleResult(query);
  }

  @Override
  public Optional<BurndownChartEntity> findCurrent() {
    TypedQuery<BurndownChartEntity> query = this.em.createNamedQuery("BurndownChartEntity.findCurrent", BurndownChartEntity.class);
    query.setParameter("now", LocalDate.now());
    return this.getOptionalSingleResult(query);
  }
}
