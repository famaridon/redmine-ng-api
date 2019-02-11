package com.famaridon.redminengapi.services.cache;

import javax.validation.constraints.NotNull;
import org.infinispan.Cache;

public interface CacheService {

  <K, V> Cache<K, V> getCache(String name);

  <K, V> Cache<K, V> getCache(String name, @NotNull org.infinispan.configuration.cache.Configuration configuration);

}
