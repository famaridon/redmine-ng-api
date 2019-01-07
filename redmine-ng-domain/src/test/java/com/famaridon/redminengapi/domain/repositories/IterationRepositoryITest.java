package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.AbstractEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.repositories.impl.AbstractJPARepository;
import com.famaridon.redminengapi.domain.repositories.impl.JPAIterationRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class IterationRepositoryITest extends AbstractJPARepositoryITest<IterationRepository, IterationEntity> {
	
	@EJB
	private IterationRepository iterationRepository;
	
	@Deployment
	public static Archive<?> createDeployment() {
		return prepare(IterationRepository.class, JPAIterationRepository.class, IterationEntity.class);
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
		iterationEntity.setStart(LocalDate.now().minus(10, ChronoUnit.DAYS));
		iterationEntity.setEnd(LocalDate.now().plus(5, ChronoUnit.DAYS));
		return iterationEntity;
	}
	
	@Override
	protected IterationEntity buildReadEntity() {
		IterationEntity iterationEntity = new IterationEntity();
		iterationEntity.setName("Read");
		iterationEntity.setNumber(2L);
		iterationEntity.setStart(LocalDate.now().minus(10, ChronoUnit.DAYS));
		iterationEntity.setEnd(LocalDate.now().plus(5, ChronoUnit.DAYS));
		return iterationEntity;
	}

	@Override
	protected IterationEntity buildUpdateEntity() {
		IterationEntity iterationEntity = new IterationEntity();
		iterationEntity.setName("To update");
		iterationEntity.setNumber(3L);
		iterationEntity.setStart(LocalDate.now().minus(10, ChronoUnit.DAYS));
		iterationEntity.setEnd(LocalDate.now().plus(5, ChronoUnit.DAYS));
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
		iterationEntity.setStart(LocalDate.now().minus(10, ChronoUnit.DAYS));
		iterationEntity.setEnd(LocalDate.now().plus(5, ChronoUnit.DAYS));
		return iterationEntity;
	}
}
