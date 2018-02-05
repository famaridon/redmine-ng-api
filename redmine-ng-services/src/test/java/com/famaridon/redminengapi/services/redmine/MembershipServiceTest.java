package com.famaridon.redminengapi.services.redmine;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.IOException;

@RunWith(Arquillian.class)
public class MembershipServiceTest extends AbstractServiceITest {
	
	@EJB
	protected MembershipService membershipService;

	@Test
	public void findByProject() throws IOException{
	this.membershipService.findByProject(this.apiKey, 372L, new Pager());
	}
}
