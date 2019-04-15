package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.DefaultUserService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class UserServiceITest extends AbstractServiceITest {

  private UserService userService;
  
  @Before
  public void setUp() {
    super.setUp();
    this.userService = new DefaultUserService(this.redmineClientConfiguration);
  }

  @Test
  public void findCurrent() throws IOException {
    User user = this.userService.findCurrent(this.apiKey);
    assertNotNull(user);
    assertNotNull(user.getId());
    assertNotNull(user.getLogin());
    assertNotNull(user.getGravatar());
    assertNotNull(user.getApiKey());
  }

  @Test
  public void findById() throws IOException {
    User user = this.userService.findById(this.apiKey, 5L);
    assertEquals(5L, user.getId());
    assertNotNull(user.getLogin());
    assertNotNull(user.getGravatar());
    assertNull(user.getApiKey());
  }

  @Test
  public void findRoles() throws IOException {
    List<String> roles = this.userService.findRoles("mockedUser");
    assertTrue(roles.contains("mock"));
  }
}
