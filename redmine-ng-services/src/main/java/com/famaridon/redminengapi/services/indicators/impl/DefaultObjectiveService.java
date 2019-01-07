package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.repositories.ObjectiveRepository;
import com.famaridon.redminengapi.services.indicators.ObjectiveService;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapper;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DefaultObjectiveService extends
    AbstractCrudRepositoryService<ObjectiveRepository, ObjectiveEntity, Objective> implements
    ObjectiveService {

  @Inject
  private IndicatorsEntityMapper indicatorsEntityMapper;
  @Inject
  private ObjectiveRepository objectiveRepository;

  @Override
  protected ObjectiveRepository getRepository() {
    return this.objectiveRepository;
  }

  @Override
  protected Objective map(ObjectiveEntity entity) {
    return this.indicatorsEntityMapper.objectiveEntityToObjective(entity);
  }

  @Override
  protected ObjectiveEntity map(Objective bean) {
    return this.indicatorsEntityMapper.objectiveToObjectiveEntity(bean);
  }

  @Override
  protected void merge(Objective bean, ObjectiveEntity entity) {
    this.indicatorsEntityMapper.updateObjectiveEntityFromObjective(bean, entity);
  }


}
