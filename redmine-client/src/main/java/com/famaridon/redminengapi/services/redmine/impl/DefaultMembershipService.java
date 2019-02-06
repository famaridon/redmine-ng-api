package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.redmine.MembershipService;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Membership;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
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
public class DefaultMembershipService extends AbstractRedmineService<Membership> implements MembershipService {
  
  @Override
  public Page<Membership> findByProject(String apiAccessKey, Long id, Pager pager)
      throws IOException {

    URIBuilder uriBuilder = this.getUriBuilder(String.format("/projects/%s/memberships.json", id));
    pager.serialize(uriBuilder);
    Page<Membership> p = Request
        .Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiAccessKey)
        .execute()
        .handleResponse(this.createPageResponseHandler());
    return p;
  }
  
  @Override
  protected Class<Membership> getBeanType()
  {
    return Membership.class;
  }
}
