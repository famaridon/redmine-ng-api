package com.famaridon.redminengapi.rest.filters;

import com.famaridon.redminengapi.rest.api.features.CacheControl;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.util.concurrent.TimeUnit;

public class CacheControlResponseFilter implements ContainerResponseFilter {
	
	private final CacheControl cacheControl;
	
	public CacheControlResponseFilter(CacheControl cacheControl) {
		this.cacheControl = cacheControl;
	}
	
	public void filter(ContainerRequestContext req, ContainerResponseContext res) {
		javax.ws.rs.core.CacheControl cc = new javax.ws.rs.core.CacheControl();
		cc.setMaxAge((int)TimeUnit.SECONDS.convert(this.cacheControl.maxAge(), this.cacheControl.maxAgeUnit()));
		cc.setPrivate(this.cacheControl.isPrivate());
		cc.setMustRevalidate(this.cacheControl.isMustRevalidate());
		cc.setNoCache(this.cacheControl.isNoCache());
		cc.setNoStore(this.cacheControl.isNoStore());
		cc.setNoTransform(this.cacheControl.isNoTransform());
		cc.setProxyRevalidate(this.cacheControl.isProxyRevalidate());
		res.getHeaders().add("Cache-Control", cc);
	}
}
