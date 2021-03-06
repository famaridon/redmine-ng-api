package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.IssuesEndpoint;
import com.famaridon.redminengapi.rest.dto.IssueDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.SimpleIndicatorDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.indicators.SimpleIndicatorService;
import com.famaridon.redminengapi.services.redmine.FilterFactory;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;

@RequestScoped
public class IssuesEndpointImpl extends AbstractRedmineEndpoint implements IssuesEndpoint {
	
	@Inject
	private IssueService issueService;
	@Inject
	private SimpleIndicatorService simpleIndicatorService;
	
	@Inject
	ConfigurationService configurationService;
	
	@Inject
	private DtoMapper mapper;
	
	@Inject
	private FilterFactory filterFactory;
	
	@Override
	public IssueDto findById(String apiKey, Long id) throws IOException {
		return this.mapper.issueToIssueDto(this.issueService.findById(apiKey, id));
	}
	
	@Override
	public PageDto<IssueDto> findAll(String apiKey, Long offset, Long limit) throws IOException {
		Page<Issue> page = this.issueService.findAll(apiKey, new Pager(offset, limit));
		PageDto<IssueDto> pageDto = this.mapper.pageToPageDto(page);
		pageDto.setElements(this.mapper.issuesToIssueDtos(page.getElements()));
		return pageDto;
	}
	
	@Override
	public PageDto<IssueDto> findByQueryAndProject(String apiKey, Long query, Long project, Long offset, Long limit) throws IOException {
		Page<Issue> page = this.issueService.findByQueryAndProject(apiKey, query, project, new Pager(offset, limit));
		PageDto<IssueDto> pageDto = this.mapper.pageToPageDto(page);
		pageDto.setElements(this.mapper.issuesToIssueDtos(page.getElements()));
		return pageDto;
	}
	
	@Override
	public SimpleIndicatorDto findCountByQueryAndProject(String apiKey, Long query, Long project) throws IOException {
		SimpleIndicatorDto simpleIndicatorDto = new SimpleIndicatorDto();
		simpleIndicatorDto.setName("Count for query : " + query + " and project : " + project);
		simpleIndicatorDto.setValue(this.issueService.findCount(apiKey, query, project));
		return simpleIndicatorDto;
	}
	
	@Override
	public SimpleIndicatorDto findCurrentOpenCountByCategory(String apiKey, Long categoryId, Long iteration) throws IOException {
		SimpleIndicatorDto simpleIndicatorDto = new SimpleIndicatorDto();
		simpleIndicatorDto.setName("Count for iteration : " + iteration + " and category : " + categoryId);
		simpleIndicatorDto.setValue(this.simpleIndicatorService.countOpenIssueByCategoryAndIteration(apiKey, categoryId, iteration));
		return simpleIndicatorDto;
	}
}