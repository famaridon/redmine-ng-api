package com.famaridon.redminengapi.services.cache.impl;

import com.famaridon.redminengapi.services.cache.CacheService;
import com.famaridon.redminengapi.services.cache.annotation.CacheConfiguration;
import com.famaridon.redminengapi.services.cache.annotation.CacheEviction;
import com.famaridon.redminengapi.services.cache.annotation.CacheExpiration;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.cache.EvictionConfigurationBuilder;
import org.infinispan.configuration.cache.ExpirationConfigurationBuilder;
import org.infinispan.manager.EmbeddedCacheManager;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.constraints.NotNull;

@Singleton
@Named
@Default
public class InfinispanCacheService implements CacheService
{
	
	private static final Configuration DEFAULT_CACHE_CONFIGURATION = new ConfigurationBuilder().build();
	
	@Resource(lookup = "java:jboss/infinispan/container/redmine-ng-api")
	private EmbeddedCacheManager cacheContainer;
	
	@Override
	public <K, V> Cache<K, V> getCache(String name)
	{
		return this.getCache(name, DEFAULT_CACHE_CONFIGURATION);
	}
	
	@Override
	public <K, V> Cache<K, V> getCache(String name, @NotNull Configuration configuration)
	{
		
		if (this.cacheContainer.getCacheConfiguration(name) == null) {
			this.cacheContainer.defineConfiguration(name, configuration);
		}
		
		return this.cacheContainer.getCache(name);
	}
	
	@Override
	@Produces
	public <K, V> Cache<K, V> getCache(InjectionPoint injectionPoint)
	{
		Annotated annotated = injectionPoint.getAnnotated();
		CacheConfiguration cacheConfiguration = annotated.getAnnotation(CacheConfiguration.class);
		if (cacheConfiguration == null) {
			return this.getCache(injectionPoint.getMember().getName());
		}
		return getCache(cacheConfiguration);
	}
	
	@Override
	public <K, V> Cache<K, V> getCache(CacheConfiguration cacheConfiguration)
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		CacheExpiration cacheExpiration = cacheConfiguration.expiration();
		if (cacheExpiration != null) {
			ExpirationConfigurationBuilder ecb = cb.expiration();
			ecb.maxIdle(cacheExpiration.maxIdle());
			ecb.lifespan(cacheExpiration.lifespan());
		}
		
		CacheEviction cacheEviction = cacheConfiguration.eviction();
		if (cacheEviction != null) {
			EvictionConfigurationBuilder ecb = cb.eviction();
			ecb.size(cacheEviction.size());
			ecb.type(cacheEviction.type());
			ecb.strategy(cacheEviction.strategy());
		}
		
		return this.getCache(cacheConfiguration.name().value(), cb.build());
	}
	
}
