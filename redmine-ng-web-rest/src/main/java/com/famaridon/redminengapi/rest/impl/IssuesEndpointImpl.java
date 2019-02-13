package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.IssuesEndpoint;
import com.famaridon.redminengapi.rest.dto.IssueDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.SimpleIndicatorDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.indicators.IterationService;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.redmine.Filter;
import com.famaridon.redminengapi.services.redmine.FilterFactory;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class IssuesEndpointImpl extends AbstractRedmineEndpoint implements IssuesEndpoint {

  @Inject
  private IssueService issueService;
  
	@Inject
	ConfigurationService configurationService;
	
	@Inject
	IterationService iterationService;

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
	public SimpleIndicatorDto findCurrentOpenCountByCategory(String apiKey, Long categoryId) throws IOException
	{
		SimpleIndicatorDto simpleIndicatorDto = new SimpleIndicatorDto();
		List<Filter> filters = new ArrayList<Filter>();
		
		Long projectId = configurationService.getLong("redmine.projects.process.project");
		Filter projectFilter = filterFactory.createProjectFilter(projectId);
		filters.add(projectFilter);
		
		Filter categoryFilter = filterFactory.createCategoryFilter(categoryId);
		filters.add(categoryFilter);
		
		Long ongoingStatusId = configurationService.getLong("redmine.status.ongoing");
		Filter statusFilter = filterFactory.createStatusFilter(ongoingStatusId);
		filters.add(statusFilter);
		
		Long iterationField = configurationService.getLong("redmine.custom-fields.iteration");
		Optional<Iteration> iteration = iterationService.findCurrent();
		if(iteration.isPresent() && iteration.get().getId() !=null){
			Filter iterationFilter = filterFactory.createCustomFieldFilter(iterationField,iteration.get().getNumber());
			filters.add(iterationFilter);
		}
		simpleIndicatorDto.setName("Count for project : " + projectId + " and iteration : " + iteration + " and category : " + categoryId);
		simpleIndicatorDto.setValue(this.issueService.findCountByFilters(apiKey,filters));
		return simpleIndicatorDto;
	}
}