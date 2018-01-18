package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpEntity;

import java.io.IOException;

public class ProjectResponseHandler extends AbstractRedmineResponseHandler<Project> {
	
	public ProjectResponseHandler(ConfigurationService configurationService) {
		super(configurationService);
	}
	
	@Override
	protected Project parse(HttpEntity entity) throws IOException {
		JsonNode holder = this.configurationService.getObjectMapper().readTree(entity.getContent());
		return this.configurationService.getObjectMapper().treeToValue(holder.get("project"), Project.class);
	}
}
