package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.ProjectsEndpoint;
import com.famaridon.redminengapi.rest.dto.MembershipDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.ProjectDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.redmine.MembershipService;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.ProjectService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Membership;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ProjectsEndpointImpl extends AbstractRedmineEndpoint implements ProjectsEndpoint {

  @Inject
  private ProjectService projectService;

  @Inject
  private MembershipService membershipService;

  @Inject
  private DtoMapper mapper;

  public ProjectDto findById(String apiKey, Long id) throws IOException {
    return this.mapper.projectToProjectDto(this.projectService.findById(apiKey, id));
  }

  public PageDto<ProjectDto> findAll(String apiKey, Long offset, Long limit) throws IOException {
    Page<Project> projectPage = this.projectService.findAll(apiKey, new Pager(offset, limit));
    PageDto<ProjectDto> projectDtoPage = this.mapper.pageToPageDto(projectPage);
    projectDtoPage.setElements(this.mapper.projectsToProjectDtos(projectPage.getElements()));
    return projectDtoPage;
  }

  @Override
  public PageDto<MembershipDto> findMembershipsById(String apiKey, Long id, Long offset, Long limit) throws IOException {
    Page<Membership> membershipPage = this.membershipService.findByProject(apiKey, id, new Pager(offset, limit));
    PageDto<MembershipDto> membershipDtoPage = this.mapper.pageToPageDto(membershipPage);
    membershipDtoPage.setElements(this.mapper.membershipsToMembershipDtos(membershipPage.getElements()));
    return membershipDtoPage;
  }

}