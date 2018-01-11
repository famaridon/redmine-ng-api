package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.ProjectDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/issues")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IssuesEndpoint {
	
	@GET
	public PageDto<ProjectDto> findAll();
	
	@GET
	@Path("/{id}")
	public ProjectDto findById(@PathParam("id") Long id);
	
	@GET
	@Path("/{id}/status")
	public ProjectDto findMembershipsById(@PathParam("id") Long id);
}
