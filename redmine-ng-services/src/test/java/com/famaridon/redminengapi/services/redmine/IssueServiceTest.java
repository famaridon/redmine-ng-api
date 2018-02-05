package com.famaridon.redminengapi.services.redmine;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.IOException;

@RunWith(Arquillian.class)
public class IssueServiceTest extends AbstractServiceITest {
	
	@EJB
	protected IssueService issueService;
	
	@Test
	public void findAll() throws IOException {
		this.issueService.findAll(this.apiKey, new Pager());
	}
	
	@Test
	public void findById() throws IOException {
		this.issueService.findById(this.apiKey, 1L);
	}
	
	@Test
	public void findByQueryAndProject() throws IOException {
		this.issueService.findByQueryAndProject(this.apiKey, 906L, 372L, new Pager());
	}
}
