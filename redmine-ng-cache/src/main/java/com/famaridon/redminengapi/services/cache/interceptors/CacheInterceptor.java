package com.famaridon.redminengapi.services.cache.interceptors;

import com.famaridon.redminengapi.services.cache.CacheService;
import com.famaridon.redminengapi.services.cache.annotation.CacheKey;
import com.famaridon.redminengapi.services.cache.annotation.CacheName;
import com.famaridon.redminengapi.services.cache.annotation.CachePut;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.infinispan.Cache;

@Interceptor
@CachePut
public class CacheInterceptor {

  @Inject
  private CacheService cacheService;

  @AroundInvoke
  public Object manageCache(InvocationContext ctx) throws Exception {
    CacheName cacheName = ctx.getMethod().getAnnotation(CacheName.class);
    Cache<String, Object> cache = this.cacheService.getCache(cacheName.value());

    StringBuilder keyBuilder = new StringBuilder(ctx.getMethod().getName());
    for (int i = 0; i < ctx.getMethod().getParameters().length; i++) {
      CacheKey cacheKey = ctx.getMethod().getParameters()[i].getAnnotation(CacheKey.class);
      if (cacheKey != null) {
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
