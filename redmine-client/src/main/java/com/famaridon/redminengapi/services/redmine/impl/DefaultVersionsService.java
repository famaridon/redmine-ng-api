package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.redmine.VersionsService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Version;
import java.io.IOException;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

@Named
@Default
public class DefaultVersionsService extends AbstractRedmineService<Version> implements VersionsService {

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
  protected Class<Version> getBeanType() {
    return Version.class;
  }
}
