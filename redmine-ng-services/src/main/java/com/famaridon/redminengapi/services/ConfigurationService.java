package com.famaridon.redminengapi.services;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

@Singleton
public class ConfigurationService {
	
	private Configuration configuration;
	
	@PostConstruct
	protected void startup() {
		Configurations configs = new Configurations();
		try {
			this.configuration = configs.properties(ConfigurationService.class.getResource("/config.properties"));
		} catch (ConfigurationException e) {
			throw new IllegalArgumentException("Can't read configuration!");
		}
	}
	
	public String getRedmineServer() {
		return this.configuration.getString("redmine.server.url");
	}

}
