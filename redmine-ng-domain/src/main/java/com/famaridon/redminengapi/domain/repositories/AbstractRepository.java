package com.famaridon.redminengapi.domain.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepository {

	@PersistenceContext(name = "redmine-domain")
	private EntityManager em;
	
}
