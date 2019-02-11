package com.famaridon.redminengapi.services.configuration.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.configuration.MockStaticConfigurationService;
import com.famaridon.redminengapi.services.redmine.RedmineClientConfiguration;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class DefaultRedmineClientConfigurationTest {

  @Inject
  private ConfigurationService mockStaticConfigurationService;

  @Inject
  private RedmineClientConfiguration redmineClientConfiguration;

  @Deployment
  public static WebArchive deployment() {
    return ShrinkWrap.create(WebArchive.class)
        .addClass(ConfigurationService.class)
        .addClass(MockStaticConfigurationService.class)
        .addClass(RedmineClientConfiguration.class)
        .addClass(DefaultRedmineClientConfiguration.class)
        .addClass(DefaultRedmineClientConfigurationTest.class)
        .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
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