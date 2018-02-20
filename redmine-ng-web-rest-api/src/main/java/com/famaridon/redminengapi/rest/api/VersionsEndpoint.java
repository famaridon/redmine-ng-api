package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.api.features.CacheControl;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.VersionDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Path("/versions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface VersionsEndpoint
{
	
	@GET
	@Path("/project/{project_id}")
	@CacheControl(maxAge = 30, maxAgeUnit = TimeUnit.MINUTES, isPrivate = true)
	public PageDto<VersionDto> findByProject(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey, @PathParam("project_id") Long projectId) throws IOException;


}
