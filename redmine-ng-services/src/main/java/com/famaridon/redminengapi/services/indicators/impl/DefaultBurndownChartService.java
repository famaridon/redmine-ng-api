package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.domain.entities.BurndownChartEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.repositories.BurndownChartRepository;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.domain.repositories.ObjectiveRepository;
import com.famaridon.redminengapi.services.exceptions.ObjectNotFoundException;
import com.famaridon.redminengapi.services.indicators.BurndownChartService;
import com.famaridon.redminengapi.services.indicators.ObjectiveService;
import com.famaridon.redminengapi.services.indicators.beans.BurndownChart;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapper;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapperImpl;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DefaultBurndownChartService extends
    AbstractCrudRepositoryService<BurndownChartRepository, BurndownChartEntity, BurndownChart> implements
    BurndownChartService {

  @Inject
  private IndicatorsEntityMapper indicatorsEntityMapper;
  @EJB
  private BurndownChartRepository burndownChartRepository;
  @EJB
  private IterationRepository iterationRepository;

  public DefaultBurndownChartService() {
  }

  public DefaultBurndownChartService(BurndownChartRepository burndownChartRepository) {
    this.burndownChartRepository = burndownChartRepository;
    this.indicatorsEntityMapper = new IndicatorsEntityMapperImpl();
  }

  @Override
  public Optional<BurndownChart> findByIteration(Long iterationId) throws ObjectNotFoundException {
    Optional<IterationEntity> iterationEntityOptional = this.iterationRepository.findById(iterationId);
    if (!iterationEntityOptional.isPresent()) {
      throw new ObjectNotFoundException("No iteration found for id " + iterationId);
    }
    Optional<BurndownChartEntity> burndownChartEntityOptional = this.burndownChartRepository
        .findByIteration(iterationEntityOptional.get());

    if (!burndownChartEntityOptional.isPresent()) {
      return Optional.empty();
    }

    return Optional.of(this.indicatorsEntityMapper.burndownChartEntityToBurndownChart(burndownChartEntityOptional.get()));
  }

  @Override
  protected BurndownChartRepository getRepository() {
    return this.burndownChartRepository;
  }

  @Override
  protected BurndownChart map(BurndownChartEntity entity) {
    return this.indicatorsEntityMapper.burndownChartEntityToBurndownChart(entity);
  }

  @Override
  protected BurndownChartEntity map(BurndownChart bean) {
    return this.indicatorsEntityMapper.burndownChartToBurndownChartEntity(bean);
  }

  @Override
  protected void merge(BurndownChart bean, BurndownChartEntity entity) {
    this.indicatorsEntityMapper.updateBurndownChartEntityFromBurndownChart(bean, entity);
  }
}
