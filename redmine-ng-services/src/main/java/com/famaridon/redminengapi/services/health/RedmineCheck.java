package com.famaridon.redminengapi.services.health;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.UserService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Health
@ApplicationScoped
public class RedmineCheck implements HealthCheck {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedmineCheck.class);
	
	private ConfigurationService configurationService;
	private UserService userService;
	
	public RedmineCheck() {
	}
	
	@Inject
	public RedmineCheck(@Named UserService userService, @Named ConfigurationService configurationService) {
		this.configurationService = configurationService;
		this.userService = userService;
	}
	
	@Override
	public HealthCheckResponse call() {
		String apiKey = this.configurationService.getString("redmine.readonlyApiKey");
		HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("redmine-server");
		try {
			User user = userService.findCurrent(apiKey);
			responseBuilder.up().withData("login", user.getLogin());
		} catch (IOException e) {
			// simply down
			LOGGER.debug("Redmine is down", e);
			responseBuilder.down();
		}
		return responseBuilder.build();
	}
}
