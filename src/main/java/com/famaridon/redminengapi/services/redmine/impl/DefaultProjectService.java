package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.rest.dto.Paginable;
import com.famaridon.redminengapi.rest.dto.ProjectDto;
import com.famaridon.redminengapi.services.redmine.ProjectService;
import com.famaridon.redminengapi.services.redmine.rest.client.dto.ProjectRDto;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.ProjectResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;

@Stateless
public class DefaultProjectService extends AbstractRedmineService<ProjectDto> implements ProjectService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultProjectService.class);
	
	@Inject
	ProjectResponseHandler projectResponseHandler;
	
	@Override
	public Paginable<ProjectRDto> findAll(String key) {
		Paginable<ProjectRDto> r = null;
		try {
			r = initGetRequest("/projects.json", key).execute().handleResponse(null);
			LOGGER.info("parsed respons is : {}", r);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	@Override
	public ProjectRDto findById(String key, Long id) {
		try {
			return initGetRequest("/projects/"+id+".json", key).execute().handleResponse(this.projectResponseHandler);
		} catch (IOException e) {
			LOGGER.error("Can't parse redmine response",e);
			throw new IllegalStateException();
		}
	}
	
}
