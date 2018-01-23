package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.UserService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.UserResponseHandler;
import org.apache.http.client.fluent.Request;
import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import java.io.IOException;

@Stateless
public class DefaultUserService extends AbstractRedmineService<User> implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultUserService.class);
	
	@Resource(lookup="java:jboss/infinispan/cache/redmine-ng-api/userByApiKey")
	private Cache<String, User> userByApiKeyCache;
	
	public DefaultUserService() {
	}
	
	public DefaultUserService(ConfigurationService configurationService) {
	this.configurationService = configurationService;
	}
	
	@Override
	public User findCurrent(String apiKey) {
		User r = this.userByApiKeyCache.get(apiKey);
		if(r == null) {
			try {
				r = Request.Get(this.configurationService.buildUrl("/users/current.json"))
					.addHeader(X_REDMINE_API_KEY, apiKey)
					.execute()
					.handleResponse(new UserResponseHandler(this.configurationService));
				this.userByApiKeyCache.put(apiKey, r);
			} catch (IOException e) {
				throw new IllegalStateException("Can't join Redmine server",e);
			}
		}
		return r;
	}

}
