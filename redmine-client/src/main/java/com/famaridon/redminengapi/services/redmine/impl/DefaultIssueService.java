package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.redmine.Filter;
import com.famaridon.redminengapi.services.redmine.FilterFactory;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

@Named
@Default
public class DefaultIssueService extends AbstractRedmineService<Issue> implements IssueService {

  @Inject
  protected FilterFactory filterFactory;

  protected URIBuilder getIssuesEndpointUriBuilder() {
    return this.getUriBuilder("/issues.json");
  }

  @Override
  public Page<Issue> findAll(String apiAccessKey, Pager pager) throws IOException {
    URIBuilder uriBuilder = this.getIssuesEndpointUriBuilder();
    pager.serialize(uriBuilder);
    return Request.Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiAccessKey)
        .execute()
        .handleResponse(this.createPageResponseHandler());
  }

  @Override
  public Issue findById(String apiAccessKey, Long id) throws IOException {
    URIBuilder uriBuilder = this.getUriBuilder(String.format("/issues/%s.json", id));
    return Request.Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiAccessKey)
        .execute()
        .handleResponse(this.createHolderResponseHandler());
  }

  @Override
  public Page<Issue> findByQueryAndProject(String apiAccessKey, Long query, Long project,
      Pager pager) throws IOException {
    List<Filter> filters = new ArrayList<>();
    if (project != null) {
      filters.add(this.filterFactory.createProjectFilter(project));
    }
    if (query != null) {
      filters.add(this.filterFactory.createQueryFilter(query));
    }
    return this.findAllByFilters(apiAccessKey, filters, pager);
  }

  @Override
  public Page<Issue> findAllByFilters(String apiAccessKey, List<Filter> filters, Pager pager)
      throws IOException {
    URIBuilder uriBuilder = this.getIssuesEndpointUriBuilder();
    pager.serialize(uriBuilder);
    filters.forEach(filter -> filter.serialize(uriBuilder));
    return Request.Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiAccessKey)
        .execute()
        .handleResponse(this.createPageResponseHandler());
  }

  @Override
  public Long findCount(String apiAccessKey, Long query, Long project) throws IOException {
    Page<Issue> p = findByQueryAndProject(apiAccessKey, query, project, new Pager());
    return p.getTotalCount();
  }

  @Override
  protected Class<Issue> getBeanType() {
    return Issue.class;
  }
  
  @Override
  public Long findCountByFilters(String apiKey, List<Filter> filters) throws IOException
  {
    Page<Issue> p = findAllByFilters(apiKey, filters, new Pager());
    return p.getTotalCount();
  }
}
