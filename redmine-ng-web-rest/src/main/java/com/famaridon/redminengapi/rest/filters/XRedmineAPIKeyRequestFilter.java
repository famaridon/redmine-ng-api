package com.famaridon.redminengapi.rest.filters;

import com.famaridon.redminengapi.rest.api.SecurityHeaders;
import com.famaridon.redminengapi.rest.security.RedmineSecurityContext;
import com.famaridon.redminengapi.services.redmine.UserService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import java.io.IOException;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import io.prometheus.client.Summary;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@PreMatching
@Priority(5000)
public class XRedmineAPIKeyRequestFilter implements ContainerRequestFilter {

  private static final Logger LOG = LoggerFactory.getLogger(XRedmineAPIKeyRequestFilter.class);
  private static final Summary API_KEYVALIDATION_TIME = Summary.build()
    .name("redmineng_api_key_validation_time")
    .help("How many time take by api key validation")
    .register();

  @Inject
  UserService userService;

  @Override
  public void filter(ContainerRequestContext requestContext) {
    String apiKey = requestContext.getHeaderString(SecurityHeaders.X_REDMINE_API_KEY);
    if (StringUtils.isBlank(apiKey)) {
      throw new SecurityException(SecurityHeaders.X_REDMINE_API_KEY + " not found!");
    }
  
    Summary.Timer timer = API_KEYVALIDATION_TIME.startTimer();
    
    try {
      User user = this.userService.findCurrent(apiKey);
      LOG.debug("{} match user {}", SecurityHeaders.X_REDMINE_API_KEY, user.getLogin());
      requestContext.setSecurityContext(this.toSecurityContext(user, requestContext.getSecurityContext()));
    } catch (IOException e) {
      LOG.error("Can't validate " + SecurityHeaders.X_REDMINE_API_KEY, e);
      throw new SecurityException("Can't validate " + SecurityHeaders.X_REDMINE_API_KEY, e);
    } finally {
      timer.observeDuration();
    }
  }

  private SecurityContext toSecurityContext(User user, SecurityContext securityContext) {
    return new RedmineSecurityContext(user, this.userService.findRoles(user.getLogin()), securityContext.isSecure());
  }
}
