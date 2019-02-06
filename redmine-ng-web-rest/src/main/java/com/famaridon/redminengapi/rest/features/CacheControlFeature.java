package com.famaridon.redminengapi.rest.features;

import com.famaridon.redminengapi.rest.api.features.CacheControl;
import com.famaridon.redminengapi.rest.filters.CacheControlResponseFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider
public class CacheControlFeature implements DynamicFeature {

  public void configure(ResourceInfo ri, FeatureContext ctx) {
    CacheControl cacheControl = ri.getResourceMethod().getAnnotation(CacheControl.class);
    if (cacheControl == null) {
      return;
    }
    CacheControlResponseFilter filter = new CacheControlResponseFilter(cacheControl);
    ctx.register(filter);
  }

}