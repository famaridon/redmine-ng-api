package com.famaridon.redminengapi.domain.hibernate;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlywayIntegrator implements Integrator {

  public static final Logger LOGGER = LoggerFactory.getLogger(FlywayIntegrator.class);

  @Override
  public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactoryImplementor,
      SessionFactoryServiceRegistry sessionFactoryServiceRegistry) {
    LOGGER.info("Starting Flyway Migration");
    Flyway flyway = Flyway.configure().dataSource(getDataSource()).load();
    flyway.migrate();
    LOGGER.info("Finished Flyway Migration");

  }

  private DataSource getDataSource() {
    try {
      return InitialContext.doLookup("java:jboss/datasources/redmine-ng");
    } catch (NamingException e) {
      throw new IllegalStateException("Missing datasource configuration", e);
    }
  }

  @Override
  public void disintegrate(SessionFactoryImplementor sessionFactoryImplementor,
      SessionFactoryServiceRegistry sessionFactoryServiceRegistry) {
    //no-op
  }
}
