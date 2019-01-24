package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.QueryService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Query;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.PageResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.io.IOException;

@Stateless
public class DefaultQueryService extends AbstractRedmineService<Query> implements QueryService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultQueryService.class);

  protected URIBuilder getQueriesEndpointUriBuilder() {
    return this.getUriBuilder("/queries.json");
  }

  @Override
  public Page<Query> findAll(String apiAccessKey, Pager pager) throws IOException {
    URIBuilder uriBuilder = this.getQueriesEndpointUriBuilder();
    pager.serialize(uriBuilder);
    Page<Query> p = Request.Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiAccessKey)
        .execute()
        .handleResponse(new PageResponseHandler<>(Query.class));
    return p;
  }

}
