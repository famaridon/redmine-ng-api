package com.famaridon.redminengapi.services.cache;

import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.eviction.EvictionType;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@InterceptorBinding
@Target({ ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheEviction
{
	long size();
	EvictionStrategy strategy();
	EvictionType type();
}
