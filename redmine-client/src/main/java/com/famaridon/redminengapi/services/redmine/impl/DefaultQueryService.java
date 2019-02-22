package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.QueryService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Query;
import java.io.IOException;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

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
    return Request.Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiAccessKey)
        .execute()
        .handleResponse(this.createPageResponseHandler());
  }

  @Override
  protected Class<Query> getBeanType() {
    return Query.class;
  }
}
