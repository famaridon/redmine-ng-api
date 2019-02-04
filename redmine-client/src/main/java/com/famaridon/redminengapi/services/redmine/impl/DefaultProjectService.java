package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.ProjectService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.HolderResponseHandler;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.PageResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Default;
import java.io.IOException;

@Default
public class DefaultProjectService extends AbstractRedmineService<Project> implements
    ProjectService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultProjectService.class);

  protected URIBuilder getProjectsEndpointUriBuilder() {
    return this.getUriBuilder("/projects.json");
  }

  @Override
  public Page<Project> findAll(String apiAccessKey, Pager pager) throws IOException {
    URIBuilder uriBuilder = this.getProjectsEndpointUriBuilder();
    pager.serialize(uriBuilder);
    uriBuilder.addParameter("include", "issue_fixed_versions,trackers,issue_categories");
    Page<Project> p = Request.Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiAccessKey)
        .execute()
        .handleResponse(new PageResponseHandler<>(Project.class));
    return p;
  }

  @Override
  public Project findById(String apiAccessKey, Long id) throws IOException {
    URIBuilder uriBuilder = this.getUriBuilder("/projects/" + id + ".json");
    uriBuilder.addParameter("include", "trackers,issue_categories");
    Project p = Request.Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiAccessKey)
        .execute()
        .handleResponse(new HolderResponseHandler<>(Project.class));
    return p;

  }

}
