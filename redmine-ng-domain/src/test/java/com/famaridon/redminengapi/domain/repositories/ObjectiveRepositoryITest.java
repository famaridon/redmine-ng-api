package com.famaridon.redminengapi.domain.repositories;

import static org.junit.Assert.assertEquals;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.repositories.impl.JPAIterationRepository;
import com.famaridon.redminengapi.domain.repositories.impl.JPAObjectiveRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
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

  @Override
  protected ObjectiveRepository getRepository() {
    return this.objectiveRepository;
  }

  @Override
  protected ObjectiveEntity buildCreateEntity() {
    ObjectiveEntity objectiveEntity = new ObjectiveEntity();
    objectiveEntity.setName("Create Objective");
    objectiveEntity.setSummary("Create Objective summary");
    objectiveEntity.setDescription("Create Objective description");
    return objectiveEntity;
  }

  @Override
  protected ObjectiveEntity buildReadEntity() {
    ObjectiveEntity objectiveEntity = new ObjectiveEntity();
    objectiveEntity.setName("Read Objective");
    objectiveEntity.setSummary("Read Objective summary");
    objectiveEntity.setDescription("Read Objective description");
    return objectiveEntity;
  }

  @Override
  protected ObjectiveEntity buildUpdateEntity() {
    ObjectiveEntity objectiveEntity = new ObjectiveEntity();
    objectiveEntity.setName("Update Objective");
    objectiveEntity.setSummary("Update Objective summary");
    objectiveEntity.setDescription("Update Objective description");
    return objectiveEntity;
  }

  @Override
  protected void modify(ObjectiveEntity entity) {
    entity.setName("Updated Objective");
    entity.setSummary("Updated Objective summary");
    entity.setDescription("Updated Objective description");
  }

  @Override
  protected void checkModification(ObjectiveEntity updated) {
    assertEquals(updated.getName(), "Updated Objective");
    assertEquals(updated.getSummary(), "Updated Objective summary");
    assertEquals(updated.getDescription(), "Updated Objective description");
  }

  @Override
  protected ObjectiveEntity buildDeleteEntity() {
    ObjectiveEntity objectiveEntity = new ObjectiveEntity();
    objectiveEntity.setName("Delete Objective");
    objectiveEntity.setSummary("Delete Objective summary");
    objectiveEntity.setDescription("Delete Objective description");
    return objectiveEntity;
  }


}
