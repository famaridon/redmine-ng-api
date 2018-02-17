package com.famaridon.redminengapi.services.cache;

import org.infinispan.Cache;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.validation.constraints.NotNull;

public interface CacheService
{
	
	<K, V> Cache<K, V> getCache(String name);
	
	<K, V> Cache<K, V> getCache(String name, @NotNull org.infinispan.configuration.cache.Configuration configuration);
	
	@Produces
	<K, V> Cache<K, V> getCache(InjectionPoint injectionPoint);
	
	<K, V> Cache<K, V> getCache(CacheConfiguration cacheConfiguration);
}
