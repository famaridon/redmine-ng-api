package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.IssueDto;
import com.famaridon.redminengapi.rest.dto.PageDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/issues")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IssuesEndpoint {
	
	@GET
	public PageDto<IssueDto> findAll(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey,
		@QueryParam(PagerParam.OFFSET) @DefaultValue("0") Long offset,
		@QueryParam(PagerParam.LIMIT) @DefaultValue("25") Long limit) throws IOException;
	
	@GET
	@Path("/{id}")
	public IssueDto findById(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey,
		@PathParam("id") Long id) throws IOException ;
	
	@GET
	@Path("/project/{project}/query/{query}")
	public PageDto<IssueDto> findByQueryAndProject(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey,
		@PathParam("query") Long query,
		@PathParam("project") Long project,
		@QueryParam(PagerParam.OFFSET) @DefaultValue("0") Long offset,
		@QueryParam(PagerParam.LIMIT) @DefaultValue("25") Long limit) throws IOException;
}
