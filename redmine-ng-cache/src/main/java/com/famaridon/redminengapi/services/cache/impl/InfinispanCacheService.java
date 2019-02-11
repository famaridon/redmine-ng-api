package com.famaridon.redminengapi.services.cache.impl;

import com.famaridon.redminengapi.services.cache.CacheService;
import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.EmbeddedCacheManager;

@Singleton
@Named
@Default
public class InfinispanCacheService implements CacheService {

  private static final Configuration DEFAULT_CACHE_CONFIGURATION = new ConfigurationBuilder().build();

  @Resource(lookup = "java:jboss/infinispan/container/redmine-ng-api")
  private EmbeddedCacheManager cacheContainer;

  @Override
  public <K, V> Cache<K, V> getCache(String name) {
    return this.getCache(name, DEFAULT_CACHE_CONFIGURATION);
  }

  @Override
  public <K, V> Cache<K, V> getCache(String name, @NotNull Configuration configuration) {

    if (this.cacheContainer.getCacheConfiguration(name) == null) {
      this.cacheContainer.defineConfiguration(name, configuration);
    }

    return this.cacheContainer.getCache(name);
  }

}
