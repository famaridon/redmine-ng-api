package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.DefaultMembershipService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

@RunWith(JUnit4.class)
public class MembershipServiceITest extends AbstractServiceITest {

  protected MembershipService membershipService;
  
  @Before
  public void setUp() {
    super.setUp();
    this.membershipService = new DefaultMembershipService(this.redmineClientConfiguration);
  }

  @Test
  public void findByProject() throws IOException {
    this.membershipService.findByProject(this.apiKey, 372L, new Pager());
  }
}
