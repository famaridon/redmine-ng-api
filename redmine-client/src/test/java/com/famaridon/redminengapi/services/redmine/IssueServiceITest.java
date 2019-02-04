package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.filter.AbstractFilter;
import com.famaridon.redminengapi.services.redmine.impl.DefaultFilterFactory;
import com.famaridon.redminengapi.services.redmine.impl.DefaultIssueService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.IOException;

@RunWith(Arquillian.class)
public class IssueServiceITest extends AbstractServiceITest {
	
	@Inject
	protected IssueService issueService;
	@Inject()
	protected FilterFactory filterFactory;

	@Deployment
	public static WebArchive createDeployment() {
		return prepareDeployment()
				.addClass(IssueServiceITest.class)
				.addClass(IssueService.class)
				.addClass(DefaultIssueService.class)
				.addClass(FilterFactory.class)
				.addClass(DefaultFilterFactory.class)
				.addPackage(AbstractFilter.class.getPackage());
				
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
