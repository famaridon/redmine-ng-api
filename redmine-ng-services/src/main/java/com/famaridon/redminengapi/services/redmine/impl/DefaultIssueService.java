package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.HolderResponseHandler;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.PageResponseHandler;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.io.IOException;

@Stateless
public class DefaultIssueService extends AbstractRedmineService<Issue> implements IssueService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultIssueService.class);
	
	public DefaultIssueService() {
	}
	
	public DefaultIssueService(ConfigurationService configurationService) {
	this.configurationService = configurationService;
	}
	
	@Override
	public Page<Issue> findAll(String apiAccessKey, Pager pager) throws IOException {
		Page<Issue> p = Request.Get(this.configurationService.buildUrl("/issues.json?%s", pager))
			.addHeader(X_REDMINE_API_KEY, apiAccessKey)
			.execute()
			.handleResponse(new PageResponseHandler<>(this.configurationService, Issue.class));
		return p;
	}
	
	@Override
	public Issue findById(String apiAccessKey, Long id) throws IOException {
		Issue p = Request.Get(this.configurationService.buildUrl("/issues/%s.json", id))
			.addHeader(X_REDMINE_API_KEY, apiAccessKey)
			.execute()
			.handleResponse(new HolderResponseHandler<>(this.configurationService, Issue.class));
		return p;
	}
	
	@Override
	public Page<Issue> findByQuery(String apiAccessKey, Long query, Pager pager) throws IOException {
		return this.findByQueryAndProject(apiAccessKey, query, null, pager);
	}
	
	@Override
	public Page<Issue> findByQueryAndProject(String apiAccessKey, Long query, Long project, Pager pager) throws IOException {
		String path;
		if (project != null) {
			path = this.configurationService.buildUrl("/issues.json?query_id=%s?%s", query, pager);
		} else {
			path = this.configurationService.buildUrl("/issues.json?query_id=%s&project_id=%s?%s", query, project, pager);
		}
		Page<Issue> p = Request.Get(path)
			.addHeader(X_REDMINE_API_KEY, apiAccessKey)
			.execute()
			.handleResponse(new PageResponseHandler<>(this.configurationService, Issue.class));
		return p;
	}
}
