package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.MembershipService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Membership;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.PageResponseHandler;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;

@Stateless
public class DefaultMembershipService extends AbstractRedmineService<Membership> implements MembershipService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMembershipService.class);
	
	@EJB
	private ConfigurationService configurationService;
	
	public DefaultMembershipService() {
	}
	
	public DefaultMembershipService(ConfigurationService configurationService) {
	this.configurationService = configurationService;
	}
	
	@Override
	public Page<Membership> findByProject(String apiAccessKey, Long id) throws IOException {
		Page<Membership> p = Request.Get(this.configurationService.buildUrl("/projects/%s/memberships.json", id))
			.addHeader(X_REDMINE_API_KEY, apiAccessKey)
			.execute()
			.handleResponse(new PageResponseHandler<>(this.configurationService, Membership.class, "memberships"));
		return p;
	}
}
