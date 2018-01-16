package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.UserService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.UserResponseHandler;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.io.IOException;

@Stateless
public class DefaultUserService extends AbstractRedmineService<User> implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserService.class);
	
	public DefaultUserService() {
	}
	
	public DefaultUserService(ConfigurationService configurationService) {
	this.configurationService = configurationService;
	}
	
	
	@Override
	public User findCurrent(String apiKey) {
		try {
			User r = Request.Get(this.configurationService.buildUrl("/users/current.json"))
			.addHeader(X_REDMINE_API_KEY, apiKey)
			.execute()
			.handleResponse(new UserResponseHandler(this.configurationService));
			return r;
		} catch (IOException e) {
			throw new IllegalStateException("Can't join Redmine server",e);
		}
	}

}
