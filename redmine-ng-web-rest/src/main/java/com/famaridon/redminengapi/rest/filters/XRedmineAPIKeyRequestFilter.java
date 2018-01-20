package com.famaridon.redminengapi.rest.filters;

import com.famaridon.redminengapi.rest.api.SecurityHeaders;
import com.famaridon.redminengapi.services.redmine.UserService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class XRedmineAPIKeyRequestFilter implements ContainerRequestFilter {
	
	private static final Logger LOG = LoggerFactory.getLogger(XRedmineAPIKeyRequestFilter.class);
	
	@EJB
	UserService userService;
	
	@Override
	public void filter(ContainerRequestContext requestContext) {
		String apiKey = requestContext.getHeaderString(SecurityHeaders.X_REDMINE_API_KEY);
		if (StringUtils.isBlank(apiKey)) {
			throw new SecurityException(SecurityHeaders.X_REDMINE_API_KEY + " not found!");
		}
		
		User user = this.userService.findCurrent(apiKey);
		LOG.info("{} match user {}", SecurityHeaders.X_REDMINE_API_KEY,user.getLogin());
	}
}
