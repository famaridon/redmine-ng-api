package com.famaridon.redminengapi.services.cache.interceptors;

import com.famaridon.redminengapi.services.cache.annotation.CacheKey;
import com.famaridon.redminengapi.services.cache.annotation.CacheName;
import com.famaridon.redminengapi.services.cache.annotation.CachePut;

public class CachedCdiBean {

  @CachePut
  public Double random() {
    return Math.random();
  }

  @CachePut
  @CacheName("randomCustom")
  public Double randomCustom() {
    return Math.random();
  }

  @CachePut
  @CacheName("withKey")
  public Double randomWithKey(@CacheKey Integer key) {
    return Math.random();
  }

  @CachePut
  @CacheName("withKeys")
  public Double randomWithKeys(@CacheKey String label, @CacheKey Integer key) {
    return Math.random();
  }
}
