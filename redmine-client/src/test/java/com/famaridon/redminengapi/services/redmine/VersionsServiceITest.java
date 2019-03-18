package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.DefaultVersionsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

@RunWith(JUnit4.class)
public class VersionsServiceITest extends AbstractServiceITest {

  private VersionsService versionsService;
  
  @Before
  public void setUp() {
    super.setUp();
    this.versionsService = new DefaultVersionsService(this.redmineClientConfiguration);
  }

  @Test
  public void testFindAll() throws IOException {
    this.versionsService.findAll(this.apiKey, 372L);
  }
}
