package com.famaridon.redminengapi.domain.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.famaridon.redminengapi.domain.entities.BurndownChartEntity;
import com.famaridon.redminengapi.domain.entities.ChartTimedValueEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.repositories.impl.JPAIterationRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import javax.ejb.EJB;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class IterationRepositoryITest extends AbstractJPARepositoryITest<IterationRepository, IterationEntity> {

  @EJB
  private IterationRepository iterationRepository;

  @Deployment
  public static Archive<?> createDeployment() {
    return prepare(IterationRepository.class, JPAIterationRepository.class, IterationEntity.class)
        .addClass(BurndownChartEntity.class)
        .addClass(ChartTimedValueEntity.class);
  }

  @Override
  protected IterationRepository getRepository() {
    return this.iterationRepository;
  }

  @Override
  protected IterationEntity buildCreateEntity() {
    IterationEntity iterationEntity = new IterationEntity();
    iterationEntity.setName("Create");
    iterationEntity.setNumber(1L);
    this.setOutdated(iterationEntity);
    return iterationEntity;
  }

  @Override
  protected IterationEntity buildReadEntity() {
    IterationEntity iterationEntity = new IterationEntity();
    iterationEntity.setName("Read");
    iterationEntity.setNumber(2L);
    this.setOutdated(iterationEntity);
    return iterationEntity;
  }

  @Override
  protected IterationEntity buildUpdateEntity() {
    IterationEntity iterationEntity = new IterationEntity();
    iterationEntity.setName("To update");
    iterationEntity.setNumber(3L);
    this.setOutdated(iterationEntity);
    return iterationEntity;
  }

  @Override
  protected void modify(IterationEntity entity) {
    entity.setName("Updated");
  }

  @Override
  protected void checkModification(IterationEntity updated) {
    assertEquals("Updated", updated.getName());
    assertEquals(Long.valueOf(3L), updated.getNumber());
  }

  @Override
  protected IterationEntity buildDeleteEntity() {
    IterationEntity iterationEntity = new IterationEntity();
    iterationEntity.setName("Delete");
    iterationEntity.setNumber(4L);
    this.setOutdated(iterationEntity);
    return iterationEntity;
  }

  protected void setOutdated(IterationEntity iterationEntity) {
    iterationEntity.setStart(LocalDate.now().minus(15, ChronoUnit.DAYS));
    iterationEntity.setEnd(LocalDate.now().minus(10, ChronoUnit.DAYS));
  }

  @Test
  public void findCurrent()
      throws HeuristicRollbackException, HeuristicMixedException, NotSupportedException, RollbackException, SystemException {
    IterationEntity iterationEntity = new IterationEntity();
    iterationEntity.setName("findCurrent");
    iterationEntity.setNumber(5L);
    iterationEntity.setStart(LocalDate.now().minus(10, ChronoUnit.DAYS));
    iterationEntity.setEnd(LocalDate.now().plus(5, ChronoUnit.DAYS));

    this.userTransaction.begin();
    IterationEntity saved = this.iterationRepository.save(iterationEntity);
    this.userTransaction.commit();

    Optional<IterationEntity> optionalEntity = this.iterationRepository.findCurrentIteration();
    assertTrue(optionalEntity.isPresent());
    assertEquals(saved, optionalEntity.get());
  }

  @Test
  public void findByNumber()
      throws HeuristicRollbackException, HeuristicMixedException, NotSupportedException, RollbackException, SystemException {
    IterationEntity iterationEntity = new IterationEntity();
    iterationEntity.setName("findByNumber");
    iterationEntity.setNumber(6L);
    this.setOutdated(iterationEntity);

    this.userTransaction.begin();
    IterationEntity saved = this.iterationRepository.save(iterationEntity);
    this.userTransaction.commit();

    Optional<IterationEntity> optionalEntity = this.iterationRepository.findByNumber(6L);
    assertTrue(optionalEntity.isPresent());
    assertEquals(saved, optionalEntity.get());
  }
}
