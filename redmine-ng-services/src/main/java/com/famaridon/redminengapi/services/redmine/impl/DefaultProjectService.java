package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.ProjectService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.PageResponseHandler;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.ProjectResponseHandler;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;

@Stateless
public class DefaultProjectService extends AbstractRedmineService<Project> implements ProjectService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultProjectService.class);
	
	@EJB
	private ConfigurationService configurationService;
	
	public DefaultProjectService() {
	}
	
	public DefaultProjectService(ConfigurationService configurationService) {
	this.configurationService = configurationService;
	}
	
	@Override
	public Page<Project> findAll(String apiAccessKey, Pager pager) throws IOException {
		Page<Project> p = Request.Get(this.configurationService.buildUrl("/projects.json?%s", pager))
			.addHeader(X_REDMINE_API_KEY, apiAccessKey)
			.execute()
			.handleResponse(new PageResponseHandler<>(this.configurationService, Project.class));
		return p;
	}
	
	@Override
	public Project findById(String apiAccessKey, Long id) throws IOException {
		Project p = Request.Get(this.configurationService.buildUrl("/projects/%s.json?include=trackers,issue_categories", id))
			.addHeader(X_REDMINE_API_KEY, apiAccessKey)
			.execute()
			.handleResponse(new ProjectResponseHandler(this.configurationService));
		return p;

	}
	
}
