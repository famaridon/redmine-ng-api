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
import java.io.IOException;

@Stateless
public class DefaultVersionsService extends AbstractRedmineService<Version> implements
    VersionsService {

  private static final Logger LOG = LoggerFactory.getLogger(DefaultVersionsService.class);

  @Override
  public Page<Version> findAll(String apiKey, Long project) throws IOException {
    URIBuilder uriBuilder = this.getUriBuilder("/projects/" + project + "/versions.json");
    Page<Version> r = Request
        .Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiKey)
        .execute()
        .handleResponse(new PageResponseHandler<>(Version.class));
    return r;
  }
}
