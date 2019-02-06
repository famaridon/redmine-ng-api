package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.MembershipDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.ProjectDto;

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

@Path("/project")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ProjectsEndpoint {
	
	@GET
	@Path("/{id}")
  ProjectDto findById(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey, @PathParam("id") Long id) throws IOException;
	
	@GET
  PageDto<ProjectDto> findAll(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey,
      @QueryParam(PagerParam.OFFSET) @DefaultValue("0") Long offset,
      @QueryParam(PagerParam.LIMIT) @DefaultValue("25") Long limit) throws IOException;
	
	@GET
	@Path("/{id}/memberships")
  PageDto<MembershipDto> findMembershipsById(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey, @PathParam("id") Long id,
      @QueryParam(PagerParam.OFFSET) @DefaultValue("0") Long offset,
      @QueryParam(PagerParam.LIMIT) @DefaultValue("25") Long limit) throws IOException;
}
