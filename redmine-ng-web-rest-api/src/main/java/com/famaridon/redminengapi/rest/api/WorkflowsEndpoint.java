package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.StatusDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/workflows")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface WorkflowsEndpoint {
	
	@GET
	@Path("/tracker/{}/")
	public PageDto<StatusDto> findBy(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey, @QueryParam(PagerParam.OFFSET) @DefaultValue("0") Long offset, @QueryParam(PagerParam.LIMIT) @DefaultValue("25") Long limit) throws IOException;
	
}
