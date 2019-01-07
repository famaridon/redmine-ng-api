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
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class IterationRepositoryITest extends AbstractJPARepositoryITest {
	
	@EJB
	private IterationRepository iterationRepository;
	
	@Inject
	private UserTransaction userTransaction;
	
	@Deployment
	public static Archive<?> createDeployment() {
		return prepare(JPAIterationRepository.class, IterationEntity.class);
	}
	
	@Test
	public void create()
		throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
		
		IterationEntity iterationEntity = new IterationEntity();
		iterationEntity.setName("Save test");
		iterationEntity.setNumber(1L);
		iterationEntity.setStart(LocalDate.now());
		iterationEntity.setEnd(LocalDate.now());
		this.userTransaction.begin();
		iterationEntity = this.iterationRepository.save(iterationEntity);
		this.userTransaction.commit();
		assertNotNull(iterationEntity.getId());
	}
	
	@Test
	public void read() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
		IterationEntity iterationEntity = new IterationEntity();
		iterationEntity.setName("Read test");
		iterationEntity.setNumber(2L);
		iterationEntity.setStart(LocalDate.now());
		iterationEntity.setEnd(LocalDate.now());
		this.userTransaction.begin();
		iterationEntity = this.iterationRepository.save(iterationEntity);
		this.userTransaction.commit();
		
		Optional<IterationEntity> read = this.iterationRepository.findById(iterationEntity.getId());
		assertTrue(read.isPresent());
		assertEquals(iterationEntity.getNumber(), read.get().getNumber());
	}
	
	@Test
	public void update()
		throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
		IterationEntity iterationEntity = new IterationEntity();
		iterationEntity.setName("Update test");
		iterationEntity.setNumber(3L);
		iterationEntity.setStart(LocalDate.now());
		iterationEntity.setEnd(LocalDate.now());
		
		this.userTransaction.begin();
		iterationEntity = this.iterationRepository.save(iterationEntity);
		this.userTransaction.commit();
		
		this.userTransaction.begin();
		iterationEntity.setName("Updated name");
		iterationEntity = this.iterationRepository.save(iterationEntity);
		this.userTransaction.commit();
		
		Optional<IterationEntity> read = this.iterationRepository.findById(iterationEntity.getId());
		assertTrue(read.isPresent());
		assertEquals("Updated name", read.get().getName());
	}
	
	@Test
	public void delete()
		throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
		IterationEntity iterationEntity = new IterationEntity();
		iterationEntity.setName("Update test");
		iterationEntity.setNumber(4L);
		iterationEntity.setStart(LocalDate.now());
		iterationEntity.setEnd(LocalDate.now());
		
		this.userTransaction.begin();
		iterationEntity = this.iterationRepository.save(iterationEntity);
		this.userTransaction.commit();
		
		
		this.userTransaction.begin();
		Optional<IterationEntity> read = this.iterationRepository.findById(iterationEntity.getId());
		assertTrue(read.isPresent());
		this.iterationRepository.delete(read.get());
		this.userTransaction.commit();
		
		read = this.iterationRepository.findById(iterationEntity.getId());
		assertFalse(read.isPresent());
	}
	
}
