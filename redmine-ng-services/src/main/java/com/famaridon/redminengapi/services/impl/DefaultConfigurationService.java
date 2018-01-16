package com.famaridon.redminengapi.services.impl;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.net.URL;

@Singleton
public class DefaultConfigurationService implements ConfigurationService {
	
	private Configuration configuration;
	private ObjectMapper objectMapper;
	
	@PostConstruct
	protected void startup() {
		init();
	}
	
	protected final void init() {
		Configurations configs = new Configurations();
		try {
			this.configuration = configs.properties(getConfigurationFile());
		} catch (ConfigurationException e) {
			throw new IllegalArgumentException("Can't read configuration!");
		}
		
		this.objectMapper = new ObjectMapper();
	}
	
	protected URL getConfigurationFile() {
		return DefaultConfigurationService.class.getResource("/config.properties");
	}
	
	@Override
	public String getString(String s) {
		return this.configuration.getString(s);
	}
	
	@Override
	public String getString(String s, String s1) {
		return this.configuration.getString(s, s1);
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
