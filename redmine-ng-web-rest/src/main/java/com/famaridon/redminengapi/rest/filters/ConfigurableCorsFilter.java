package com.famaridon.redminengapi.rest.filters;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
@Priority(0) // CORS must be the really first of all
public class ConfigurableCorsFilter extends org.jboss.resteasy.plugins.interceptors.CorsFilter {

  @Inject
  ConfigurationService configurationService;

  @PostConstruct
  private void init() {
    this.allowedOrigins = new HashSet<>(this.configurationService.getList(String.class, "http.cors.allowedOrigins"));
    this.corsMaxAge = (int) TimeUnit.SECONDS.convert(10, TimeUnit.MINUTES);
    this.allowCredentials = false;
  }

}
