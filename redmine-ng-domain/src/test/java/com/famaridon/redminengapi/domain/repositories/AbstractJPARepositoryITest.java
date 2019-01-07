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
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public abstract class AbstractJPARepositoryITest<R extends Repository<E>, E extends AbstractEntity> {
	
	@Inject
	protected UserTransaction userTransaction;
	
	public static final String[] REDMINE_CLIENT_DEPENDENCIES = new String[]{
		"org.apache.commons:commons-lang3"
	};
	
	protected static Archive<?> prepare(Class<? extends Repository> repositoryInterface,
		Class<? extends AbstractJPARepository> repositoryClass, Class<? extends AbstractEntity> entityClass) {
		
		File[] dependencies = Maven.resolver()
			.loadPomFromFile(new File("pom.xml"))
			.resolve(REDMINE_CLIENT_DEPENDENCIES)
			.withTransitivity().asFile();
		
		return ShrinkWrap.create(WebArchive.class).addClass(AbstractJPARepositoryITest.class).addClass(AbstractEntity.class)
			.addAsLibraries(dependencies)
			.addClass(entityClass)
			.addClass(Repository.class)
			.addClass(repositoryInterface)
			.addClass(AbstractJPARepository.class)
			.addClass(repositoryClass).addAsResource("test-persistence.xml", "META-INF/persistence.xml")
			.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	protected abstract R getRepository();
	
	@Test
	public void create()
		throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
		E entity = this.buildCreateEntity();
		this.userTransaction.begin();
		E saved = this.getRepository().save(entity);
		this.userTransaction.commit();
		entity.setId(saved.getId());
		assertEquals(entity, saved);
	}
	
	protected abstract E buildCreateEntity();
	
	@Test
	public void read() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
		E entity = this.buildReadEntity();
		this.userTransaction.begin();
		E saved = this.getRepository().save(entity);
		this.userTransaction.commit();
		
		Optional<E> read = this.getRepository().findById(saved.getId());
		assertTrue(read.isPresent());
		entity.setId(saved.getId());
		assertEquals(entity, read.get());
	}
	
	protected abstract E buildReadEntity();

	@Test
	public void update()
			throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
		E entity = this.buildUpdateEntity();

		this.userTransaction.begin();
		E saved = this.getRepository().save(entity);
		this.userTransaction.commit();

		this.modify(saved);

		this.userTransaction.begin();
		E updated = this.getRepository().save(saved);
		this.userTransaction.commit();

		Optional<E> read = this.getRepository().findById(saved.getId());
		assertTrue(read.isPresent());
		this.checkModification(updated);
	}

	protected abstract E buildUpdateEntity();

	protected abstract void modify(E entity);

	protected abstract void checkModification(E updated);

	@Test
	public void delete()
			throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
		E entity = this.buildDeleteEntity();

		this.userTransaction.begin();
		E saved = this.getRepository().save(entity);
		this.userTransaction.commit();

		this.userTransaction.begin();
		Optional<E> read = this.getRepository().findById(saved.getId());
		assertTrue(read.isPresent());
		this.getRepository().delete(read.get());
		this.userTransaction.commit();

		read = this.getRepository().findById(saved.getId());
		assertFalse(read.isPresent());
	}

	protected abstract E buildDeleteEntity();

}