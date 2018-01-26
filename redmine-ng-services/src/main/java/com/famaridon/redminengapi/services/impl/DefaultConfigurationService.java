package com.famaridon.redminengapi.services.impl;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.eviction.EvictionType;
import org.infinispan.manager.EmbeddedCacheManager;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import java.net.URL;
import java.util.List;

@Singleton
public class DefaultConfigurationService implements ConfigurationService {
	
	@Resource(lookup = "java:jboss/infinispan/container/redmine-ng-api")
	private EmbeddedCacheManager cacheContainer;
	
	private Configuration configuration;
	private ObjectMapper objectMapper;
	
	@PostConstruct
	protected void startup() {
		init();
	}
	
	protected final void init() {
		Configurations configs = new Configurations();
		try {
			this.configuration = configs.fileBased(JSONConfiguration.class,getConfigurationFile());
		} catch (ConfigurationException e) {
			throw new IllegalArgumentException("Can't read configuration!");
		}
		
		this.objectMapper = new ObjectMapper();
	}
	
	protected URL getConfigurationFile() {
		return DefaultConfigurationService.class.getResource("/config.json");
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
	public <K, V> Cache<K, V> getCache(String name) {
		Cache<K, V> cache = this.cacheContainer.getCache(name, false);
		if(cache == null) {
			this.cacheContainer.defineConfiguration(name, new ConfigurationBuilder().expiration().maxIdle(18000).eviction().type(EvictionType.COUNT).size(50).strategy(EvictionStrategy.LRU).build());
			cache = this.cacheContainer.getCache(name, true);
		}
		return cache;
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
