package com.famaridon.redminengapi.services.indicators.impl;

import static org.mockito.Mockito.mock;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.services.indicators.IterationService;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class IterationServiceUTest extends
    AbstractCrudServiceUTest<IterationService, Iteration, IterationRepository, IterationEntity> {

  protected IterationService iterationService;
  protected IterationRepository mockedIterationRepository;

  @Before
  public void initialize() {
    this.mockedIterationRepository = mock(IterationRepository.class);
    this.iterationService = new DefaultIterationService(this.mockedIterationRepository);
  }

  @Override
  protected IterationService getService() {
    return this.iterationService;
  }

  @Override
  protected IterationRepository getRepository() {
    return this.mockedIterationRepository;
  }

  @Override
  protected IterationEntity buildEntity() {
    LocalDate now = LocalDate.now();
    IterationEntity iterationEntity = new IterationEntity();
    iterationEntity.setId(0L);
    iterationEntity.setName("entity");
    iterationEntity.setNumber(1L);
    iterationEntity.setStart(now);
    iterationEntity.setEnd(now);
    return iterationEntity;
  }

  @Override
  protected Iteration buildBean() {
    LocalDate now = LocalDate.now();
    Iteration iteration = new Iteration();
    iteration.setId(0L);
    iteration.setName("entity");
    iteration.setNumber(1L);
    iteration.setStart(now);
    iteration.setEnd(now);
    return iteration;
  }
}