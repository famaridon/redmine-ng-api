package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.IterationDto;
import com.famaridon.redminengapi.rest.dto.PageDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/iteration")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IterationEndpoint
{
	@GET
	public PageDto<IterationDto> findAll(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey) throws IOException;

	@POST
	public IterationDto create(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey, IterationDto iterationDto) ;

	@PUT
	@Path("/{id}")
	public IterationDto update(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey, IterationDto iterationDto) ;
}
