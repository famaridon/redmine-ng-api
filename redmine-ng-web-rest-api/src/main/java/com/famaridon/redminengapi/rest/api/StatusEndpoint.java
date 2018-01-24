package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.api.features.CacheControl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Path("/status")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface StatusEndpoint {
	
	@GET
	@CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.HOURS)
	public Response findAll(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey) throws IOException;
	
}
