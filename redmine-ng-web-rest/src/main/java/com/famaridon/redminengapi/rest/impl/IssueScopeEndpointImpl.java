package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.IssueScopeEndpoint;
import com.famaridon.redminengapi.rest.dto.IssueScopeDto;
import com.famaridon.redminengapi.services.indicators.IssueScopeService;
import com.famaridon.redminengapi.services.indicators.beans.IssueScope;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class IssueScopeEndpointImpl extends AbstractCrudEndpoint<IssueScopeDto, IssueScopeService, IssueScope> implements
    IssueScopeEndpoint {

  @Inject
  private IssueScopeService issueScopeService;

  @Override
  protected IssueScopeService getService() {
    return this.issueScopeService;
  }

  @Override
  protected IssueScope dtoToBean(IssueScopeDto dto) {
    return this.mapper.issueScopeDtoToIssueScope(dto);
  }

  @Override
  protected IssueScopeDto beanToDto(IssueScope bean) {
    return this.mapper.issueScopeToIssueScopeDto(bean);
  }
}
