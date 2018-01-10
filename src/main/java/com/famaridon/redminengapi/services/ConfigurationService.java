package com.famaridon.redminengapi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class ConfigurationService {
	
	private Configuration configuration;
	private ObjectMapper objectMapper;
	
	@PostConstruct
	protected void startup() {
		Configurations configs = new Configurations();
		try {
			this.configuration = configs.properties(ConfigurationService.class.getResource("/config.properties"));
		} catch (ConfigurationException e) {
			throw new IllegalArgumentException("Can't read configuration!");
		}
		
		this.objectMapper = new ObjectMapper();
		
	}
	
	public String getRedmineServer() {
		return this.configuration.getString("redmine.server.url");
	}
	
	/**
	 * get {@link ConfigurationService#objectMapper} property
	 *
	 * @return get the objectMapper property
	 **/
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
}
