package com.famaridon.redminengapi.services.cache;

import org.infinispan.Cache;

import javax.validation.constraints.NotNull;

public interface CacheService
{
	
	<K, V> Cache<K, V> getCache(String name);
	
	<K, V> Cache<K, V> getCache(String name, @NotNull org.infinispan.configuration.cache.Configuration configuration);
}
