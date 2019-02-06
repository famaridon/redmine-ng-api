package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.DefaultQueryService;
import java.io.IOException;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class QueryServiceITest extends AbstractServiceITest {

  @Inject
  private QueryService queryService;

  @Deployment
  public static WebArchive createDeployment() {
    return prepareDeployment()
        .addClass(QueryServiceITest.class)
        .addClass(QueryService.class)
        .addClass(DefaultQueryService.class);
  }

  @Test
  public void findAll() throws IOException {
    this.queryService.findAll(this.apiKey, new Pager());
  }
}
