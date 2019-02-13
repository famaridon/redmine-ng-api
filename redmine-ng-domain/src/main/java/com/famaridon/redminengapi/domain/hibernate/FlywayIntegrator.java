package com.famaridon.redminengapi.domain.hibernate;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class FlywayIntegrator implements Integrator {

  @Override
  public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactoryImplementor,
      SessionFactoryServiceRegistry sessionFactoryServiceRegistry) {

  }

  @Override
  public void disintegrate(SessionFactoryImplementor sessionFactoryImplementor,
      SessionFactoryServiceRegistry sessionFactoryServiceRegistry) {

  }
}
