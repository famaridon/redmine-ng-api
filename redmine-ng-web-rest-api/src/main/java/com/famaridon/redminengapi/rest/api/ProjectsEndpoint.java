package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.Paginable;
import com.famaridon.redminengapi.rest.dto.ProjectDto;
import com.famaridon.redminengapi.rest.dto.TrackerDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
	public ProjectDto findById(@PathParam("id") Long id);
	
	@GET
	public Paginable<ProjectDto> findAll();
	
	@GET
	@Path("/{id}/trackers")
	public Paginable<TrackerDto> findTrackersById(@PathParam("id") Long id);
	
	@GET
	@Path("/{id}/memberships")
	public ProjectDto findMembershipsById(@PathParam("id") Long id);
}
