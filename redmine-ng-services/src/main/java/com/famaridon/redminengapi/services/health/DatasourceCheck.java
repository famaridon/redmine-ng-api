package com.famaridon.redminengapi.services.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Health
@ApplicationScoped
public class DatasourceCheck implements HealthCheck {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatasourceCheck.class);
	
	@Resource(lookup = "java:jboss/datasources/redmine-ng")
	private DataSource dataSource;
	
	@Override
	public HealthCheckResponse call() {
		HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("datasource");
		try (Connection connection = this.dataSource.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement("SELECT 1;");
				 ResultSet resultSet = preparedStatement.executeQuery()) {
			resultSet.next();
			return responseBuilder.up().build();
		} catch (SQLException e) {
			// simply down
			LOGGER.debug("Datasource is down", e);
			responseBuilder.down();
		}
		return responseBuilder.build();
	}
}
