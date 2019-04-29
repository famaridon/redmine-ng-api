package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.IssueContextEndpoint;
import com.famaridon.redminengapi.rest.dto.IssueContextDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.services.exceptions.ObjectNotFoundException;
import com.famaridon.redminengapi.services.indicators.IssueContextService;
import com.famaridon.redminengapi.services.indicators.beans.IssueContext;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@RequestScoped
public class IssueContextEndpointImpl extends AbstractCrudEndpoint<IssueContextDto, IssueContextService, IssueContext> implements
    IssueContextEndpoint {

  @Inject
  private IssueContextService issueContextService;

  @Override
  protected IssueContextService getService() {
    return this.issueContextService;
  }

  @Override
  protected IssueContext dtoToBean(IssueContextDto dto) {
    return this.mapper.issueContextDtoToIssueContext(dto);
  }

  @Override
  protected IssueContextDto beanToDto(IssueContext bean) {
    return this.mapper.issueContextToIssueContextDto(bean);
  }

  @Override
  public PageDto<IssueContextDto> findAllByScopeId(Long scopeId, Long offset, Long limit) {
    try {
      Page<IssueContext> page = this.issueContextService.findAllByScopeId(scopeId, new Pager(offset, limit));
      PageDto<IssueContextDto> pageDto = this.mapper.pageToPageDto(page);
      pageDto.setElements(this.mapper.issueContextsToIssueContextDtos(page.getElements()));
      return pageDto;
    } catch (ObjectNotFoundException e) {
      throw new NotFoundException(e.getMessage(), e);
    }
  }
}
