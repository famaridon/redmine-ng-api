package com.famaridon.redminengapi.services.configuration;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;

@Singleton
public class MockStaticConfigurationService implements ConfigurationService {

  @Override
  public String getString(String key) {
    if("redmine.server.url".equals(key)) {
      return "https://example/com";
    } else {
      throw new UnsupportedOperationException();
    }
  }

  @Override
  public String getString(String key, String def) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Long getLong(String key) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Long getLong(String key, Long def) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> List<T> getList(Class<T> type, String key) {
    throw new UnsupportedOperationException();
  }

  @Override
  public <T> List<T> getList(Class<T> type, String key, List<T> defaultValues) {
    if(type.equals(String.class) && "security.users.mock".equals(key)) {
      List<String> roles = new ArrayList<>();
      roles.add("admin");
      return (List<T>) roles;
    } else {
      throw new UnsupportedOperationException();
    }
  }

}
