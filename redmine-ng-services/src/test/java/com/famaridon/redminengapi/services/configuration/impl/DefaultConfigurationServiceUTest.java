package com.famaridon.redminengapi.services.configuration.impl;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class DefaultConfigurationServiceUTest {
  
  protected ConfigurationService configurationService;
  
  @Before
  public void setUp() {
    this.configurationService = new DefaultConfigurationService();
    ((DefaultConfigurationService)this.configurationService).startup();
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

}