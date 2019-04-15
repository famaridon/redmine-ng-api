package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.DefaultFilterFactory;
import com.famaridon.redminengapi.services.redmine.impl.DefaultIssueService;
import com.famaridon.redminengapi.services.redmine.mock.TestRedmineClientConfiguration;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class IssueServiceITest extends AbstractServiceITest {
	
	protected IssueService issueService;
	protected FilterFactory filterFactory;
	protected RedmineClientConfiguration redmineClientConfiguration;
	
	@Before
	public void setUp() {
		super.setUp();
		this.redmineClientConfiguration = new TestRedmineClientConfiguration();
		this.filterFactory = new DefaultFilterFactory();
		this.issueService = new DefaultIssueService(this.redmineClientConfiguration, this.filterFactory);
		
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
	public void findByIdWithJournal() throws IOException {
		Issue issue = this.issueService.findById(this.apiKey, 1L, IssueAssociatedData.JOURNALS);
		assertNotNull(issue.getJournals());
	}
	
	@Test
	public void findByQueryAndProject() throws IOException {
		this.issueService.findByQueryAndProject(this.apiKey, 906L, 372L, new Pager());
	}
	
	@Test
	public void findCount() throws IOException {
		Long count = this.issueService.findCount(this.apiKey, 1762L, 1032L);
	}
	
	@Test
	public void findCountByFilters() throws IOException {
		List<Filter> filters = new ArrayList<>();
		filters.add(filterFactory.createProjectFilter(372L));
		Long count = this.issueService.findCountByFilters(this.apiKey, filters);
	}
	
}
