package com.famaridon.redminengapi.services.configuration;

import org.apache.commons.lang3.NotImplementedException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Startup
@Singleton
public class MockConfigurationService implements ConfigurationService {

  private Map<String, String> configuration;

  @PostConstruct
  protected void startup() {
    init();
  }

  protected final void init() {
    this.configuration = new HashMap<>();
    this.configuration.put("redmine.server.url", System.getenv("TEST_REDMINE_SERVER"));
  }

  @Override
  public String getString(String key) {
    return this.configuration.get(key);
  }

  @Override
  public String getString(String key, String def) {
    return this.configuration.getOrDefault(key, def);
  }

  @Override
  public Long getLong(String key) {
    throw new NotImplementedException("");
  }

  @Override
  public Long getLong(String key, Long def) {
    throw new NotImplementedException("");
  }

  @Override
  public <T> List<T> getList(Class<T> type, String key) {
    throw new NotImplementedException("Mock not implemented");
  }

  @Override
  public <T> List<T> getList(Class<T> type, String key, List<T> defaultValues) {
    throw new NotImplementedException("Mock not implemented");
  }

}
