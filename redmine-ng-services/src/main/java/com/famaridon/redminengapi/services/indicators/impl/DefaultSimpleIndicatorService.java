package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.indicators.SimpleIndicatorService;
import com.famaridon.redminengapi.services.redmine.Filter;
import com.famaridon.redminengapi.services.redmine.FilterFactory;
import com.famaridon.redminengapi.services.redmine.IssueService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DefaultSimpleIndicatorService implements SimpleIndicatorService {
  
  @Inject
  private ConfigurationService configurationService;
  @Inject
  private IssueService issueService;
  @Inject
  private FilterFactory filterFactory;
  
  @Override
  public Long countOpenIssueByCategoryAndIteration(String apiKey, Long categoryId, Long iteration) throws IOException {
    List<Filter> filters = new ArrayList<Filter>();
  
    Long projectId = this.configurationService.getLong("redmine.projects.process.project");
    Filter projectFilter = filterFactory.createProjectFilter(projectId);
    filters.add(projectFilter);
  
    Filter categoryFilter = filterFactory.createCategoryFilter(categoryId);
    filters.add(categoryFilter);
  
    List<Long> statusIds = new ArrayList<>();
    statusIds.add(configurationService.getLong("redmine.status.ongoing"));
    statusIds.add(configurationService.getLong("redmine.status.reopen"));
    Filter statusFilter = filterFactory.createStatusFilter(statusIds);
    filters.add(statusFilter);
  
    Long iterationField = configurationService.getLong("redmine.custom-fields.iteration");
    Filter iterationFilter = filterFactory.createCustomFieldFilter(iterationField,iteration);
    filters.add(iterationFilter);
  
    return this.issueService.findCountByFilters(apiKey,filters);
  }
}

