package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.rest.client.dto.ProjectRDto;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.http.HttpEntity;

import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.IOException;

@SessionScoped
public class ProjectResponseHandler extends AbstractRedmineResponseHandler<ProjectRDto> {
	
	@Inject
	private ConfigurationService configurationService;
	
	@Override
	protected ProjectRDto parse(HttpEntity entity) throws IOException {
		throw new NotImplementedException("");
		//		return configurationService.getObjectMapper().readValue(entity.getContent(), RedmineProjectHolder.class).project;
	}
}
