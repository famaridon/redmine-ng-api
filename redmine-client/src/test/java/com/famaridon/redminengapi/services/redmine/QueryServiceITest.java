package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.DefaultQueryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.inject.Inject;
import java.io.IOException;

@RunWith(JUnit4.class)
public class QueryServiceITest extends AbstractServiceITest {

  @Inject
  private QueryService queryService;
  
  @Before
  public void setUp() {
    super.setUp();
    this.queryService = new DefaultQueryService(this.redmineClientConfiguration);
  }

  @Test
  public void findAll() throws IOException {
    this.queryService.findAll(this.apiKey, new Pager());
  }
}
