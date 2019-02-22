package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.VersionsEndpoint;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.VersionDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.redmine.VersionsService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Version;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class VersionsEndpointImpl extends AbstractRedmineEndpoint implements VersionsEndpoint {

  @Inject
  private VersionsService versionsService;

  @Inject
  private DtoMapper mapper;

  @Override
  public PageDto<VersionDto> findByProject(String apiKey, Long projectId) throws IOException {
    Page<Version> page = this.versionsService.findAll(apiKey, projectId);
    PageDto<VersionDto> pageDto = this.mapper.pageToPageDto(page);

    pageDto.setElements(this.mapper.versionsToVersionDtos(page.getElements()));

    return pageDto;
  }
}