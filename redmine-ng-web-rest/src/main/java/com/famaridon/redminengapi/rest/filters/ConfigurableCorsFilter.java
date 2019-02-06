package com.famaridon.redminengapi.rest.filters;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@Provider
@PreMatching
@Priority(0) // CORS must be the really first of all
public class ConfigurableCorsFilter extends org.jboss.resteasy.plugins.interceptors.CorsFilter {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConfigurableCorsFilter.class);
	
	@Inject
	ConfigurationService configurationService;
	
	@PostConstruct
	private void init() {
		this.allowedOrigins = new HashSet<>(this.configurationService.getList(String.class, "http.cors.allowedOrigins"));
		this.corsMaxAge = (int)TimeUnit.SECONDS.convert(10, TimeUnit.MINUTES);
		this.allowCredentials = false;
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		super.filter(requestContext);
	}
}
