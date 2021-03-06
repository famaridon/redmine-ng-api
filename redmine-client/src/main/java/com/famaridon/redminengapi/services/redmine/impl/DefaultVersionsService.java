package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.redmine.RedmineClientConfiguration;
import com.famaridon.redminengapi.services.redmine.VersionsService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Version;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@Default
public class DefaultVersionsService extends AbstractRedmineService<Version> implements VersionsService {
  
  @Inject
  public DefaultVersionsService(RedmineClientConfiguration redmineClientConfiguration) {
    super(redmineClientConfiguration);
  }
  
  @Override
  public Page<Version> findAll(String apiKey, Long project) throws IOException {
    URIBuilder uriBuilder = this.getUriBuilder("/projects/" + project + "/versions.json");
    return Request
        .Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiKey)
        .execute()
        .handleResponse(this.createPageResponseHandler());
  }

  @Override
  public Version findById(String apiKey, Long version) throws IOException {
    URIBuilder uriBuilder = this.getUriBuilder(String.format("/versions/%s.json", version));
    return Request
        .Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiKey)
        .execute()
        .handleResponse(this.createHolderResponseHandler());
  }

  @Override
  protected Class<Version> getBeanType() {
    return Version.class;
  }
}
