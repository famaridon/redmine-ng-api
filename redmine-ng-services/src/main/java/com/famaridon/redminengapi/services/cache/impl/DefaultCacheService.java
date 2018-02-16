package com.famaridon.redminengapi.services.cache.impl;

import com.famaridon.redminengapi.services.cache.CacheService;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.eviction.EvictionType;
import org.infinispan.manager.EmbeddedCacheManager;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.validation.constraints.NotNull;

@Singleton
public class DefaultCacheService implements CacheService
{
	@Resource(lookup = "java:jboss/infinispan/container/redmine-ng-api")
	private EmbeddedCacheManager cacheContainer;
	
	@Override
	public <K, V> Cache<K, V> getCache(String name) {
		Cache<K, V> cache = this.cacheContainer.getCache(name, false);
		if(cache == null) {
			this.cacheContainer.defineConfiguration(name, new ConfigurationBuilder().expiration().maxIdle(18000).eviction().type(EvictionType.COUNT).size(50).strategy(EvictionStrategy.LRU).build());
			cache = this.cacheContainer.getCache(name, true);
			cache.start();
		}
		return cache;
	}
	
	@Override
	public <K, V> Cache<K, V> getCache(String name, @NotNull org.infinispan.configuration.cache.Configuration configuration) {
		Cache<K, V> cache = this.cacheContainer.getCache(name, false);
		if(cache == null) {
			this.cacheContainer.defineConfiguration(name, configuration);
			cache = this.cacheContainer.getCache(name, true);
			cache.start();
		}
		return cache;
	}
	
}
