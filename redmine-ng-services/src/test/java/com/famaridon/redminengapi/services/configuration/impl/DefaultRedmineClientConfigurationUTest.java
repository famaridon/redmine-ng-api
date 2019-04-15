package com.famaridon.redminengapi.services.configuration.impl;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.RedmineClientConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class DefaultRedmineClientConfigurationUTest {
  
  protected ConfigurationService mockConfigurationService;
  private RedmineClientConfiguration redmineClientConfiguration;
  
  @Before
  public void setUp() {
    this.mockConfigurationService = mock(ConfigurationService.class);
    this.redmineClientConfiguration = new DefaultRedmineClientConfiguration(this.mockConfigurationService);
    when(this.mockConfigurationService.getString("redmine.server.url")).thenReturn("https://example/com");
    when(this.mockConfigurationService.getList(eq(String.class), startsWith("security.users."), any())).thenReturn(Collections.singletonList("admin"));

  }
  
  @Test
  public void getServerUrl() {
    assertEquals("https://example/com", this.redmineClientConfiguration.getServerUrl());
  }

  @Test
  public void getUserRoles() {
    List<String> roles =  this.redmineClientConfiguration.getUserRoles("mock");
    assertTrue(roles.contains("admin"));
  }
}