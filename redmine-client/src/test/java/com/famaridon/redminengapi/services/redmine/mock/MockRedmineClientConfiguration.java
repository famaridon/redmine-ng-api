package com.famaridon.redminengapi.services.redmine.mock;

import com.famaridon.redminengapi.services.redmine.RedmineClientConfiguration;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Default;

@Default
public class MockRedmineClientConfiguration implements RedmineClientConfiguration {

  @Override
  public String getServerUrl() {
    return System.getenv("TEST_REDMINE_SERVER");
  }

  @Override
  public List<String> getUserRoles(String login) {
    List<String> roles = new ArrayList<>();
    roles.add("mock");
    return roles;
  }
}
