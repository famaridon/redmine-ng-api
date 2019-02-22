package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.DefaultMembershipService;
import java.io.IOException;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MembershipServiceITest extends AbstractServiceITest {

  @Inject
  protected MembershipService membershipService;

  @Deployment
  public static WebArchive createDeployment() {
    return prepareDeployment()
        .addClass(MembershipServiceITest.class)
        .addClass(MembershipService.class)
        .addClass(DefaultMembershipService.class);
  }

  @Test
  public void findByProject() throws IOException {
    this.membershipService.findByProject(this.apiKey, 372L, new Pager());
  }
}
