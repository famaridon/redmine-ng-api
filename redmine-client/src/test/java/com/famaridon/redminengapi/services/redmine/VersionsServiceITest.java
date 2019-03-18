package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.DefaultVersionsService;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Arquillian.class)
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
