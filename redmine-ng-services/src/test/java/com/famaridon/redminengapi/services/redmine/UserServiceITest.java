package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.DefaultUserService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.IOException;

@RunWith(Arquillian.class)
public class UserServiceITest extends AbstractServiceITest {
	
	@EJB
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
		this.userService.findCurrent(this.apiKey);
	}
	
	@Test
	public void findById() throws IOException {
		this.userService.findById(this.apiKey, 5L);
	}
}
