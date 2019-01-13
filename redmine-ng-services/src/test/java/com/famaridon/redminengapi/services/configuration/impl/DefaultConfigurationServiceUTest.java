package com.famaridon.redminengapi.services.configuration.impl;

import static org.junit.Assert.*;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class DefaultConfigurationServiceUTest {

  @EJB
  protected ConfigurationService configurationService;

  public static final String[] REDMINE_CLIENT_DEPENDENCIES = new String[]{
      "org.apache.commons:commons-lang3",
      "org.apache.commons:commons-configuration2",
      "commons-beanutils:commons-beanutils",
      "org.slf4j:slf4j-api"
  };

  @Deployment
  public static WebArchive deployment() {
    File[] dependencies = Maven.resolver()
        .loadPomFromFile(new File("pom.xml"))
        .resolve(REDMINE_CLIENT_DEPENDENCIES)
        .withTransitivity().asFile();

    return ShrinkWrap.create(WebArchive.class)
        .addClass(ConfigurationService.class)
        .addClass(DefaultConfigurationService.class)
        .addClass(DefaultConfigurationServiceUTest.class)
        .addAsLibraries(dependencies)
        .addAsResource("config-utest.json", "config.json")
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
  }

  @Test
  public void getString() {
    String value = this.configurationService.getString("string");
    assertEquals("A string", value);
  }

  @Test
  public void getDefaultString() {
    String value = this.configurationService.getString("unknown", "default");
    assertEquals("default", value);
  }

  @Test
  public void getStringList() {
    List<String> values = this.configurationService.getList(String.class, "string-list");
    assertArrayEquals(new String[]{"A", "B", "C"}, values.toArray());
  }

  @Test
  public void getDefaultList() {
    List<String> values = this.configurationService
        .getList(String.class, "unknown-list", Arrays.asList("A", "B", "C"));
    assertArrayEquals(new String[]{"A", "B", "C"}, values.toArray());
  }

  @Test
  public void buildUrl() {
    String url = this.configurationService.buildUrl("/api/v1/echo");
    assertEquals("https://projects.visiativ.com/api/v1/echo", url);
  }

  @Test
  public void buildUrlWithParameter() {
    String url = this.configurationService.buildUrl("/api/v1/echo?message=%1s","very nice message");
    assertEquals("https://projects.visiativ.com/api/v1/echo?message=very nice message", url);
  }

}