package com.famaridon.redminengapi.services.redmine.mock;

import com.famaridon.redminengapi.services.redmine.RedmineClientConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TestRedmineClientConfiguration implements RedmineClientConfiguration {
  
  private final String server;
  
  public TestRedmineClientConfiguration() {
    this.server = System.getenv("TEST_REDMINE_SERVER");
    assertNotNull(this.server);
  }
  
  @Override
  public String getServerUrl() {
    return this.server;
  }

  @Override
  public List<String> getUserRoles(String login) {
    List<String> roles = new ArrayList<>();
    roles.add("mock");
    return roles;
  }
}
