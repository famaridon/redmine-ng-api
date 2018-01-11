package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.ProjectsEndpoint;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.ProjectDto;
import com.famaridon.redminengapi.rest.dto.TrackerDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.redmine.ProjectService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;
import org.apache.commons.lang3.NotImplementedException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;

@RequestScoped
public class ProjectsEndpointImpl extends AbstractRedmineEndpoint implements ProjectsEndpoint {
	
	@EJB
	private ProjectService projectService;
	
	@Inject
	private DtoMapper mapper;
	
	public ProjectDto findById(@PathParam("id") Long id) {
		ProjectDto p = this.mapper.projectToProjectDto(this.projectService.findById("***REMOVED***", id));
		return p;
	}
	
	public PageDto<ProjectDto> findAll() {
		Page<Project> projectPage = this.projectService.findAll("***REMOVED***");
		PageDto<ProjectDto> projectDtoPage = this.mapper.pageToPageDto(projectPage);
		projectDtoPage.setElements(this.mapper.projectsToProjectDtos(projectPage.getElements()));
		return projectDtoPage;
	}
	
	public PageDto<TrackerDto> findTrackersById(@PathParam("id") Long id) {
		throw new NotImplementedException("");
	}
	
	public ProjectDto findMembershipsById(@PathParam("id") Long id) {
		throw new NotImplementedException("");
	}
	
}