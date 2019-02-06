package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.redmine.VersionsService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Version;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.PageResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import java.io.IOException;

@Named
@Default
public class DefaultVersionsService extends AbstractRedmineService<Version> implements VersionsService {

  @Override
  public Page<Version> findAll(String apiKey, Long project) throws IOException {
    URIBuilder uriBuilder = this.getUriBuilder("/projects/" + project + "/versions.json");
    Page<Version> r = Request
        .Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiKey)
        .execute()
        .handleResponse(this.createPageResponseHandler());
    return r;
  }
  
  @Override
  protected Class<Version> getBeanType()
  {
    return Version.class;
  }
}
