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
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public abstract class AbstractJPARepositoryITest {
	
	protected static Archive<?> prepare(Class<? extends AbstractJPARepository> repositoryClass, Class<? extends AbstractEntity> entityClass) {
		return ShrinkWrap.create(WebArchive.class)
			.addClass(AbstractJPARepositoryITest.class)
			.addClass(AbstractEntity.class)
			.addClass(entityClass)
			.addClass(Repository.class)
			.addClass(IterationRepository.class)
			.addClass(AbstractJPARepository.class)
			.addClass(repositoryClass)
			.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
			.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
}
