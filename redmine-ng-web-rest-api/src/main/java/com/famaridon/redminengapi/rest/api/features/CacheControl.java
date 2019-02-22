package com.famaridon.redminengapi.rest.api.features;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheControl {

  int maxAge();

  TimeUnit maxAgeUnit() default TimeUnit.SECONDS;

  boolean isPrivate() default false;

  boolean isMustRevalidate() default false;

  boolean isNoCache() default false;

  boolean isNoStore() default false;

  boolean isNoTransform() default true;

  boolean isProxyRevalidate() default true;
}
