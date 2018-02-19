package com.famaridon.redminengapi.services.cache.interceptors;

import com.famaridon.redminengapi.services.cache.CacheKey;
import com.famaridon.redminengapi.services.cache.CacheName;
import com.famaridon.redminengapi.services.cache.CachePut;
import com.famaridon.redminengapi.services.cache.CacheService;
import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@CachePut
public class CacheInterceptor {
	
	private static final Logger LOG = LoggerFactory.getLogger(CacheInterceptor.class);
	
	@EJB
	private CacheService cacheService;
	
	@AroundInvoke
	public Object manageCache(InvocationContext ctx) throws Exception {
		CacheName cacheName = ctx.getMethod().getAnnotation(CacheName.class);
		Cache<String, Object> cache = this.cacheService.getCache(cacheName.value());
		
		StringBuilder keyBuilder = new StringBuilder(ctx.getMethod().getName());
		for (int i = 0; i < ctx.getMethod().getParameters().length; i++) {
			CacheKey cacheKey = ctx.getMethod().getParameters()[i].getAnnotation(CacheKey.class);
			if(cacheKey != null){
				keyBuilder.append('-').append(ctx.getParameters()[i]);
			}
		}
		
		Object result = cache.get(keyBuilder.toString());
		if (result == null) {
			result = ctx.proceed();
			cache.put(keyBuilder.toString(), result);
		}
		
		return result;
	}
	
}