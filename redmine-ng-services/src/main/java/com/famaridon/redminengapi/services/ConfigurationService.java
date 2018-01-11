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
	
	protected String getRedmineServer() {
		return this.configuration.getString("redmine.server.url");
	}
	
	public String buildUrl(String path, Object... parameters) {
		return this.getRedmineServer() + String.format(path, parameters);
	}
	
	public ObjectMapper getObjectMapper() {
		return this.objectMapper;
	}
}
