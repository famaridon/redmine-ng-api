package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.domain.entities.BurndownChartEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.repositories.BurndownChartRepository;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.services.indicators.IterationService;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapper;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapperImpl;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DefaultIterationService extends
    AbstractCrudRepositoryService<IterationRepository, IterationEntity, Iteration> implements
    IterationService {

  @Inject
  protected IterationRepository iterationRepository;
  @Inject
  protected BurndownChartRepository burndownChartRepository;
  @Inject
  private IndicatorsEntityMapper indicatorsEntityMapper;

  public DefaultIterationService() {
  }

  public DefaultIterationService(IterationRepository iterationRepository) {
    this.iterationRepository = iterationRepository;
    this.indicatorsEntityMapper = new IndicatorsEntityMapperImpl();
  }

  @Override
  public Iteration create(Iteration bean) {
    IterationEntity entity = this.map(bean);
    IterationEntity saved = this.getRepository().save(entity);
    BurndownChartEntity burndownChartEntity = new BurndownChartEntity();
    burndownChartEntity.setIteration(saved);
    this.burndownChartRepository.save(burndownChartEntity);
    return this.map(saved);
  }

  @Override
  protected IterationRepository getRepository() {
    return this.iterationRepository;
  }

  @Override
  protected Iteration map(IterationEntity entity) {
    return this.indicatorsEntityMapper.iterationEntityToIteration(entity);
  }

  @Override
  protected IterationEntity map(Iteration bean) {
    return this.indicatorsEntityMapper.iterationToIterationEntity(bean);
  }

  @Override
  protected void merge(Iteration bean, IterationEntity entity) {
    this.indicatorsEntityMapper.updateIterationEntityFromIteration(bean, entity);
  }

  @Override
  public Optional<Iteration> findCurrent() {
    Optional<IterationEntity> entityOptional = this.iterationRepository.findCurrentIteration();
    return entityOptional.map(
        iterationEntity -> this.indicatorsEntityMapper.iterationEntityToIteration(iterationEntity));
  }
}
