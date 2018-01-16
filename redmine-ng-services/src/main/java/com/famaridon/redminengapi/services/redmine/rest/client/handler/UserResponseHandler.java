package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpEntity;

import javax.enterprise.context.RequestScoped;
import java.io.IOException;

@RequestScoped
public class UserResponseHandler extends AbstractRedmineResponseHandler<User> {
	
	public UserResponseHandler(ConfigurationService configurationService) {
		super(configurationService);
	}
	
	@Override
	protected User parse(HttpEntity entity) throws IOException {
		JsonNode holder = this.configurationService.getObjectMapper().readTree(entity.getContent());
		return this.configurationService.getObjectMapper().treeToValue(holder.get("user"), User.class);
	}
}
