package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.QueryService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Query;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.PageResponseHandler;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;

@Stateless
public class DefaultQueryService extends AbstractRedmineService<Query> implements QueryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultQueryService.class);
	
	@EJB
	private ConfigurationService configurationService;
	
	public DefaultQueryService() {
	}
	
	public DefaultQueryService(ConfigurationService configurationService) {
	this.configurationService = configurationService;
	}
	
	@Override
	public Page<Query> findAll(String apiAccessKey, Pager pager) throws IOException {
		Page<Query> p = Request.Get(this.configurationService.buildUrl("/queries.json?%s", pager))
			.addHeader(X_REDMINE_API_KEY, apiAccessKey)
			.execute()
			.handleResponse(new PageResponseHandler<>(this.configurationService, Query.class, "queries"));
		return p;
	}
	
}
