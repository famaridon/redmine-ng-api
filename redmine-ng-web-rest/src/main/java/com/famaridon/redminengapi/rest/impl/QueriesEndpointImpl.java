package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.QueriesEndpoint;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.QueryDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.QueryService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Query;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class QueriesEndpointImpl extends AbstractRedmineEndpoint implements QueriesEndpoint {

  @Inject
  private QueryService queryService;

  @Inject
  private DtoMapper mapper;

  @Override
  public PageDto<QueryDto> findAll(String apiKey, Long offset, Long limit) throws IOException {
    Page<Query> page = this.queryService.findAll(apiKey, new Pager(offset, limit));
    PageDto<QueryDto> pageDto = this.mapper.pageToPageDto(page);
    pageDto.setElements(this.mapper.queriesToQueryDtos(page.getElements()));
    return pageDto;
  }
}