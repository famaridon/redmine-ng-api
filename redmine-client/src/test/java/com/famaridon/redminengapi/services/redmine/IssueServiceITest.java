package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.filter.AbstractFilter;
import com.famaridon.redminengapi.services.redmine.impl.DefaultFilterFactory;
import com.famaridon.redminengapi.services.redmine.impl.DefaultIssueService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Detail;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Journal;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class IssueServiceITest extends AbstractServiceITest {

  @Inject
  protected IssueService issueService;
  @Inject
  protected FilterFactory filterFactory;

  @Deployment
  public static WebArchive createDeployment() {
    WebArchive webArchive =  prepareDeployment()
        .addClass(IssueServiceITest.class)
        .addClass(DefaultIssueService.class)
        .addClass(IssueService.class)
        .addClass(FilterFactory.class)
        .addClass(DefaultFilterFactory.class)
        .addClass(StatusType.class)
        .addClass(Journal.class)
        .addClass(Detail.class)
        .addClass(User.class)
        .addPackage(AbstractFilter.class.getPackage());
  
    System.out.println(webArchive.toString(true));
    
    return webArchive;

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
  public void findCountByFilters() throws IOException{
    List<Filter> filters = new ArrayList<>();
    filters.add(filterFactory.createProjectFilter(372L));
    Long count = this.issueService.findCountByFilters(this.apiKey, filters);
  }
  
}
