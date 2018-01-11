package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.ConfigurationService;
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
	
	@Override
	public Page<Project> findAll(String apiAccessKey) {
		try {
			Page<Project> p = Request.Get(this.configurationService.buildUrl("/projects.json"))
				.addHeader("X-Redmine-API-Key", apiAccessKey)
				.execute()
				.handleResponse(new PageResponseHandler<>(this.configurationService, Project.class));
			return p;
		} catch (IOException e) {
			throw new IllegalStateException("Can't join Redmine server",e);
		}
	}
	
	@Override
	public Project findById(String apiAccessKey, Long id) {
		try {
			Project p = Request.Get(this.configurationService.buildUrl("/projects/%s.json", id))
			.addHeader("X-Redmine-API-Key", apiAccessKey)
			.execute()
			.handleResponse(new ProjectResponseHandler(this.configurationService));
			return p;
		} catch (IOException e) {
			throw new IllegalStateException("Can't join Redmine server",e);
		}
	}
	
}
