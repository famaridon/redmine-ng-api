package com.famaridon.redminengapi.domain.repositories;

import static org.junit.Assert.assertEquals;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.repositories.impl.JPAObjectiveRepository;
import java.time.LocalDate;
import javax.ejb.EJB;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ObjectiveRepositoryITest extends
    AbstractJPARepositoryITest<ObjectiveRepository, ObjectiveEntity> {

  @EJB
  private ObjectiveRepository objectiveRepository;

  @Deployment
  public static Archive<?> createDeployment() {
    return prepare(ObjectiveRepository.class, JPAObjectiveRepository.class, ObjectiveEntity.class)
        .addClass(IterationEntity.class);
  }

  @Before
  public void setup()
      throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
    this.userTransaction.begin();
    this.em.merge(this.buildParentIteration());
    this.userTransaction.commit();
  }

  @Test
  public void findAllByIteration()
      throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
    ObjectiveEntity objective1 = this.buildCreateEntity();
    ObjectiveEntity objective2 = this.buildCreateEntity();
    objective1.setName("findAllByIteration 1");
    objective2.setName("findAllByIteration 2");
    this.userTransaction.begin();
    this.getRepository().save(objective1);
    this.getRepository().save(objective2);
    this.userTransaction.commit();

    Iterable<ObjectiveEntity> objectiveEntities = this.objectiveRepository.findAllByIteration(this.buildParentIteration(), 0L, 25L);
    int count = 0;
    for (ObjectiveEntity objectiveEntity : objectiveEntities) {
      count++;
    }

    assertEquals(2, count);
  }

  @Override
  protected ObjectiveRepository getRepository() {
    return this.objectiveRepository;
  }

  @Override
  protected ObjectiveEntity buildCreateEntity() {
    ObjectiveEntity objectiveEntity = new ObjectiveEntity();
    objectiveEntity.setName("Create Objective");
    objectiveEntity.setDescription("Create Objective description");
    objectiveEntity.setIteration(this.buildParentIteration());
    return objectiveEntity;
  }

  protected IterationEntity buildParentIteration() {
    IterationEntity iterationEntity = new IterationEntity();
    iterationEntity.setId(1L);
    iterationEntity.setName("Parent iteration");
    iterationEntity.setNumber(1L);
    iterationEntity.setStart(LocalDate.now());
    iterationEntity.setEnd(LocalDate.now());
    return iterationEntity;
  }

  @Override
  protected ObjectiveEntity buildReadEntity() {
    ObjectiveEntity objectiveEntity = new ObjectiveEntity();
    objectiveEntity.setName("Read Objective");
    objectiveEntity.setDescription("Read Objective description");
    objectiveEntity.setIteration(this.buildParentIteration());
    return objectiveEntity;
  }

  @Override
  protected ObjectiveEntity buildUpdateEntity() {
    ObjectiveEntity objectiveEntity = new ObjectiveEntity();
    objectiveEntity.setName("Update Objective");
    objectiveEntity.setDescription("Update Objective description");
    objectiveEntity.setIteration(this.buildParentIteration());
    return objectiveEntity;
  }

  @Override
  protected void modify(ObjectiveEntity entity) {
    entity.setName("Updated Objective");
    entity.setDescription("Updated Objective description");
  }

  @Override
  protected void checkModification(ObjectiveEntity updated) {
    assertEquals("Updated Objective", updated.getName());
    assertEquals("Updated Objective description", updated.getDescription());
  }

  @Override
  protected ObjectiveEntity buildDeleteEntity() {
    ObjectiveEntity objectiveEntity = new ObjectiveEntity();
    objectiveEntity.setName("Delete Objective");
    objectiveEntity.setDescription("Delete Objective description");
    objectiveEntity.setIteration(this.buildParentIteration());
    return objectiveEntity;
  }


}
