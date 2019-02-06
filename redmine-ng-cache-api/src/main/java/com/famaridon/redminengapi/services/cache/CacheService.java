package com.famaridon.redminengapi.services.cache;

import com.famaridon.redminengapi.services.cache.annotation.CacheConfiguration;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.validation.constraints.NotNull;
import org.infinispan.Cache;

public interface CacheService {

  <K, V> Cache<K, V> getCache(String name);

  <K, V> Cache<K, V> getCache(String name, @NotNull org.infinispan.configuration.cache.Configuration configuration);

  @Produces
  <K, V> Cache<K, V> getCache(InjectionPoint injectionPoint);

  <K, V> Cache<K, V> getCache(CacheConfiguration cacheConfiguration);
}
