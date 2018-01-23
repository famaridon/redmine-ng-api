package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.ProjectsEndpoint;
import com.famaridon.redminengapi.rest.dto.MembershipDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.ProjectDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.redmine.MembershipService;
import com.famaridon.redminengapi.services.redmine.ProjectService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Membership;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;

@RequestScoped
public class ProjectsEndpointImpl extends AbstractRedmineEndpoint implements ProjectsEndpoint {
	
	@EJB
	private ProjectService projectService;
	
	@EJB
	private MembershipService membershipService;
	
	@Inject
	private DtoMapper mapper;
	
	public ProjectDto findById(String apiKey, Long id) throws IOException {
		ProjectDto p = this.mapper.projectToProjectDto(this.projectService.findById(apiKey, id));
		return p;
	}
	
	public PageDto<ProjectDto> findAll(String apiKey) throws IOException {
		Page<Project> projectPage = this.projectService.findAll(apiKey);
		PageDto<ProjectDto> projectDtoPage = this.mapper.pageToPageDto(projectPage);
		projectDtoPage.setElements(this.mapper.projectsToProjectDtos(projectPage.getElements()));
		return projectDtoPage;
	}
	
	@Override
	public PageDto<MembershipDto> findMembershipsById(String apiKey, Long id) throws IOException {
		Page<Membership> membershipPage = this.membershipService.findByProject(apiKey, id);
		PageDto<MembershipDto> membershipDtoPage = this.mapper.pageToPageDto(membershipPage);
		membershipDtoPage.setElements(this.mapper.membershipsToMembershipDtos(membershipPage.getElements()));
		return membershipDtoPage;
	}
	
}