package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.DefaultIssueService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.IOException;

@RunWith(Arquillian.class)
public class IssueServiceITest extends AbstractServiceITest {
	
	@EJB
	protected IssueService issueService;

	@Deployment
	public static WebArchive createDeployment() {
		return prepareDeployment()
				.addClass(IssueServiceITest.class)
				.addClass(IssueService.class)
				.addClass(DefaultIssueService.class);
	}

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
	
	@Test
	public void findCount() throws IOException {
		Long count = this.issueService.findCount(this.apiKey, 1762L, 1032L);
	}
}
