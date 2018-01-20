package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.ProjectDto;
import com.famaridon.redminengapi.rest.dto.TrackerDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/projects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ProjectsEndpoint {
	
	@GET
	@Path("/{id}")
	public ProjectDto findById(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey, @PathParam("id") Long id);
	
	@GET
	public PageDto<ProjectDto> findAll(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey);
	
	@GET
	@Path("/{id}/trackers")
	public PageDto<TrackerDto> findTrackersById(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey, @PathParam("id") Long id);
	
	@GET
	@Path("/{id}/memberships")
	public ProjectDto findMembershipsById(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey, @PathParam("id") Long id);
}
