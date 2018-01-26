package com.famaridon.redminengapi.services.impl;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.infinispan.manager.CacheContainer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import java.net.URL;
import java.util.List;

@Singleton
public class DefaultConfigurationService implements ConfigurationService {
	
	@Resource(lookup = "java:jboss/infinispan/container/redmine-ng-api")
	private CacheContainer cacheContainer;
	
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
	public String getString(String key) {
		return this.configuration.getString(key);
	}
	
	@Override
	public <T> List<T> getList(Class<T> type, String key) {
		return this.configuration.getList(type, key);
	}
	
	@Override
	public <T> List<T> getList(Class<T> type, String key, List<T> defaultValues) {
		return this.configuration.getList(type, key, defaultValues);
	}
	
	@Override
	public String getString(String key, String def) {
		return this.configuration.getString(key, def);
	}
	
	@Override
	public CacheContainer getCacheContainer() {
		return this.cacheContainer;
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
