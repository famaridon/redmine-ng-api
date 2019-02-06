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

import javax.enterprise.inject.Default;
import javax.inject.Named;
import java.io.IOException;

@Named
@Default
public class DefaultQueryService extends AbstractRedmineService<Query> implements QueryService {

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
        .handleResponse(this.createPageResponseHandler());
    return p;
  }
  
  @Override
  protected Class<Query> getBeanType()
  {
    return Query.class;
  }
}
