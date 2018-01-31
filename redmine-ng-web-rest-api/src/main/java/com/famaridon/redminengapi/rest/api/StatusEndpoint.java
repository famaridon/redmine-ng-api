package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.api.features.CacheControl;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.StatusDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Path("/status")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface StatusEndpoint {
	
	@GET
	@Path("/tracker/{tracker}/available")
	@CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
	public PageDto<StatusDto> findAvailableByTracker(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey,
		@PathParam("tracker") Long trackerId) throws IOException;
	
	@GET
	@Path("/tracker/{tracker}/status/{status}/available")
	@CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
	public PageDto<StatusDto> findAvailableByTrackerAndStatus(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey,
		@PathParam("tracker") Long trackerId,
		@PathParam("status") Long status) throws IOException;
	
}
