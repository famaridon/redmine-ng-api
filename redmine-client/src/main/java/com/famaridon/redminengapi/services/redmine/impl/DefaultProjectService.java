package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.ProjectService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;
import java.io.IOException;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

@Named
@Default
public class DefaultProjectService extends AbstractRedmineService<Project> implements ProjectService {

  protected URIBuilder getProjectsEndpointUriBuilder() {
    return this.getUriBuilder("/projects.json");
  }

  @Override
  public Page<Project> findAll(String apiAccessKey, Pager pager) throws IOException {
    URIBuilder uriBuilder = this.getProjectsEndpointUriBuilder();
    pager.serialize(uriBuilder);
    uriBuilder.addParameter("include", "issue_fixed_versions,trackers,issue_categories");
    return Request.Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiAccessKey)
        .execute()
        .handleResponse(this.createPageResponseHandler());
  }

  @Override
  public Project findById(String apiAccessKey, Long id) throws IOException {
    URIBuilder uriBuilder = this.getUriBuilder("/projects/" + id + ".json");
    uriBuilder.addParameter("include", "trackers,issue_categories");
    return Request.Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiAccessKey)
        .execute()
        .handleResponse(this.createHolderResponseHandler());

  }

  @Override
  protected Class<Project> getBeanType() {
    return Project.class;
  }
}
