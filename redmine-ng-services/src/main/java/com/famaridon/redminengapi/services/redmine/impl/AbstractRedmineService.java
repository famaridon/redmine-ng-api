package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;

public abstract class AbstractRedmineService<T> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRedmineService.class);

	@EJB
	protected ConfigurationService configurationService;
	
	public AbstractRedmineService() {
	}
	

	
}
