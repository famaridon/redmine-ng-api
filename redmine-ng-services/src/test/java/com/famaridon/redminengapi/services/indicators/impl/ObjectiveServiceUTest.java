package com.famaridon.redminengapi.services.indicators.impl;

import static org.mockito.Mockito.mock;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.domain.repositories.ObjectiveRepository;
import com.famaridon.redminengapi.services.indicators.IterationService;
import com.famaridon.redminengapi.services.indicators.ObjectiveService;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ObjectiveServiceUTest extends
    AbstractCrudServiceUTest<ObjectiveService, Objective, ObjectiveRepository, ObjectiveEntity> {

  protected ObjectiveService objectiveService;
  protected ObjectiveRepository mockedObjectiveRepository;

  @Before
  public void initialize() {
    this.mockedObjectiveRepository = mock(ObjectiveRepository.class);
    this.objectiveService = new DefaultObjectiveService(this.mockedObjectiveRepository);
  }

  @Override
  protected ObjectiveService getService() {
    return this.objectiveService;
  }

  @Override
  protected ObjectiveRepository getRepository() {
    return this.mockedObjectiveRepository;
  }

  @Override
  protected ObjectiveEntity buildEntity() {
    ObjectiveEntity objectiveEntity = new ObjectiveEntity();
    objectiveEntity.setId(0L);
    objectiveEntity.setName("entity");
    objectiveEntity.setDescription("A very nice description");
    objectiveEntity.setSummary("A short summary");
    return objectiveEntity;
  }

  @Override
  protected Objective buildBean() {
    Objective objective = new Objective();
    objective.setId(0L);
    objective.setName("bean");
    objective.setDescription("A very nice description");
    objective.setSummary("A short summary");
    return objective;
  }
}