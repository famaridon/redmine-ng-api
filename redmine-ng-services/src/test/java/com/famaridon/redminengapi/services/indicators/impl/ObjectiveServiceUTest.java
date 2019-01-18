package com.famaridon.redminengapi.services.indicators.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.domain.repositories.ObjectiveRepository;
import com.famaridon.redminengapi.services.exceptions.ObjectNotFoundException;
import com.famaridon.redminengapi.services.indicators.IterationService;
import com.famaridon.redminengapi.services.indicators.ObjectiveService;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import com.famaridon.redminengapi.services.redmine.Pager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ObjectiveServiceUTest extends
    AbstractCrudServiceUTest<ObjectiveService, Objective, ObjectiveRepository, ObjectiveEntity> {

  protected ObjectiveService objectiveService;
  protected ObjectiveRepository mockedObjectiveRepository;
  protected IterationRepository mockedIterationRepository;

  @Before
  public void initialize() {
    this.mockedObjectiveRepository = mock(ObjectiveRepository.class);
    this.mockedIterationRepository = mock(IterationRepository.class);
    this.objectiveService = new DefaultObjectiveService(this.mockedObjectiveRepository);
  }

  @Test
  public void findAllByIterationId() throws ObjectNotFoundException {
    List<ObjectiveEntity> objectiveEntities = new ArrayList<>();
    objectiveEntities.add(this.buildEntity());
    objectiveEntities.add(this.buildEntity());
    when(this.mockedObjectiveRepository.findAllByIteration(any(), 0L,25L)).thenReturn(objectiveEntities);
    when(this.mockedIterationRepository.findById(1L)).thenReturn(Optional.of(this.buildParentIterationEntity()));

    this.objectiveService.findAllByIterationId(1L, new Pager());

    verify(this.mockedObjectiveRepository).findAllByIteration(any(),0L, 25L);
    verify(this.mockedIterationRepository).findById(any());
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
    objectiveEntity.setIteration(this.buildParentIterationEntity());
    return objectiveEntity;
  }

  protected IterationEntity buildParentIterationEntity() {
    IterationEntity parent = new IterationEntity();
    parent.setId(1L);
    parent.setName("Parent iteration");
    return parent;
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

  @Override
  protected void validate(ObjectiveEntity entity, Objective bean) {
    assertEquals(entity.getId(), bean.getId());
    assertEquals(entity.getName(), bean.getName());
    assertEquals(entity.getDescription(), bean.getDescription());
    assertEquals(entity.getSummary(), bean.getSummary());
    assertNotNull(entity.getIteration());
    assertNotNull(bean.getIteration());
    assertEquals(entity.getIteration().getId(), bean.getIteration().getId());
  }
}