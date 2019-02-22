package com.famaridon.redminengapi.services.redmine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.famaridon.redminengapi.services.redmine.impl.DefaultUserService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class UserServiceITest extends AbstractServiceITest {

  @Inject
  private UserService userService;

  @Deployment
  public static WebArchive createDeployment() {
    return prepareDeployment()
        .addClass(UserServiceITest.class)
        .addClass(UserService.class)
        .addClass(DefaultUserService.class);
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
