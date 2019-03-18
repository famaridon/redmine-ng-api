package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.DefaultProjectService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

@RunWith(JUnit4.class)
public class ProjectServiceITest extends AbstractServiceITest {

  ProjectService projectService;
  
  @Before
  public void setUp() {
    super.setUp();
    this.projectService = new DefaultProjectService(this.redmineClientConfiguration);
  }

  @Test
  public void findById() throws IOException {
    this.projectService.findById(this.apiKey, 372L);
  }

  @Test
  public void findAll() throws IOException {
    this.projectService.findAll(this.apiKey, new Pager());
  }
}
