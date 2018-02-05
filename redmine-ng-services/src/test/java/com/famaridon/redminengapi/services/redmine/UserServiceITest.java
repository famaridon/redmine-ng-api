package com.famaridon.redminengapi.services.redmine;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.IOException;

@RunWith(Arquillian.class)
public class UserServiceITest extends AbstractServiceITest {
	
	@EJB
	private UserService userService;
	
	@Test
	public void findCurrent() throws IOException {
		this.userService.findCurrent(this.apiKey);
	}
	
	@Test
	public void findById() throws IOException {
		this.userService.findById(this.apiKey, 5L);
	}
}
