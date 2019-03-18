package com.famaridon.redminengapi.services.configuration.impl;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.RedmineClientConfiguration;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;

@Named
@Default
public class DefaultRedmineClientConfiguration implements RedmineClientConfiguration {

  private ConfigurationService configurationService;
  
  @Inject
  public DefaultRedmineClientConfiguration(@Named ConfigurationService configurationService) {
    this.configurationService = configurationService;
  }
  
  @Override
  public String getServerUrl() {
    return this.configurationService.getString("redmine.server.url");
  }

  @Override
  public List<String> getUserRoles(String login) {
    return this.configurationService.getList(String.class, "security.users." + login, Collections.emptyList());
  }
}
