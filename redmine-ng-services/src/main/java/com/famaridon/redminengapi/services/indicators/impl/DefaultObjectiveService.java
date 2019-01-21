package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.domain.repositories.ObjectiveRepository;
import com.famaridon.redminengapi.services.exceptions.ObjectNotFoundException;
import com.famaridon.redminengapi.services.indicators.ObjectiveService;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapper;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapperImpl;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;

import java.util.Optional;
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
  @Inject
  private IterationRepository iterationRepository;

  public DefaultObjectiveService() {
  }

  public DefaultObjectiveService(ObjectiveRepository objectiveRepository) {
    this.objectiveRepository = objectiveRepository;
    this.indicatorsEntityMapper = new IndicatorsEntityMapperImpl();
  }

  @Override
  public Page<Objective> findAllByIterationId(Long iterationId, Pager pager)
      throws ObjectNotFoundException {
    Optional<IterationEntity> iterationEntity = this.iterationRepository.findById(iterationId);
    if (!iterationEntity.isPresent()) {
      throw new ObjectNotFoundException("No iteration found for id " + iterationId);
    }
    Iterable<ObjectiveEntity> objectiveEntities = this.objectiveRepository
        .findAllByIteration(iterationEntity.get(), pager.getOffset(), pager.getLimit());
    return this.toPage(objectiveEntities, pager);
  }

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
