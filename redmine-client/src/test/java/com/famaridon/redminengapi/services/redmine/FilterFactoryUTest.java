package com.famaridon.redminengapi.services.redmine;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.famaridon.redminengapi.services.redmine.impl.DefaultFilterFactory;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Query;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FilterFactoryUTest {

  private FilterFactory filterFactory;
  private URIBuilder uriBuilder;

//	@Deployment
//	public static WebArchive createDeployment() {
//		WebArchive webArchive = ShrinkWrap.create(WebArchive.class)
//			.addClass(FilterFactory.class)
//			.addClass(DefaultFilterFactory.class)
//			.addClass(QueryParamSerializable.class)
//			.addClass(URIBuilder.class)
//			.addClass(Filter.class)
//			.addClass(StatusType.class)
//			.addPackage(AbstractFilter.class.getPackage())
//			.addPackages(true, AbstractRedmineBean.class.getPackage())
//			.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
//
//		return webArchive;
//	}

  @Before
  public void initialize() {
    this.filterFactory = new DefaultFilterFactory();
    this.uriBuilder = mock(URIBuilder.class);
  }

  @Test
  public void createProjectFilterWithLong() {
    Filter f = this.filterFactory.createProjectFilter(10l);
    f.serialize(uriBuilder);
    verify(uriBuilder).addParameter("project_id", "10");
  }

  @Test
  public void createProjectFilterWithProject() {
    Project p = new Project();
    p.setId(10);
    p.setName("mock");
    Filter f = this.filterFactory.createProjectFilter(p);
    f.serialize(uriBuilder);
    verify(uriBuilder).addParameter("project_id", "10");
  }

  @Test
  public void createQueryFilterWithLong() {
    Filter f = this.filterFactory.createQueryFilter(10l);
    f.serialize(uriBuilder);
    verify(uriBuilder).addParameter("query_id", "10");
  }

  @Test
  public void createQueryFilterWithQuery() {
    Query query = new Query();
    query.setId(10L);
    query.setName("mock");
    Filter f = this.filterFactory.createQueryFilter(query);
    f.serialize(uriBuilder);
    verify(uriBuilder).addParameter("query_id", "10");
  }

  @Test
  public void createStatusFilterWithLong() {
    Filter f = this.filterFactory.createStatusFilter(10l);
    f.serialize(uriBuilder);
    verify(uriBuilder).addParameter("status_id", "10");
  }

  @Test
  public void createStatusFilterWithStatus() {
    Status status = new Status();
    status.setId(10L);
    status.setName("mock");
    Filter f = this.filterFactory.createStatusFilter(status);
    f.serialize(uriBuilder);
    verify(uriBuilder).addParameter("status_id", "10");
  }

  @Test
  public void createStatusFilterWithStatusTypeAll() {
    Filter f = this.filterFactory.createStatusFilter(StatusType.ALL);
    f.serialize(uriBuilder);
    verify(uriBuilder).addParameter("status_id", "*");
  }

  @Test
  public void createStatusFilterWithStatusTypeClosed() {
    Filter f = this.filterFactory.createStatusFilter(StatusType.CLOSED);
    f.serialize(uriBuilder);
    verify(uriBuilder).addParameter("status_id", "closed");
  }

  @Test
  public void createStatusFilterWithStatusTypeOpen() {
    Filter f = this.filterFactory.createStatusFilter(StatusType.OPEN);
    f.serialize(uriBuilder);
    verify(uriBuilder).addParameter("status_id", "open");
  }

  @Test
  public void createTrackerFilterWithLong() {
    Filter f = this.filterFactory.createTrackerFilter(10l);
    f.serialize(uriBuilder);
    verify(uriBuilder).addParameter("tracker_id", "10");
  }

  @Test
  public void createTrackerFilterWithLongArray() {
    List<Long> trackers = new ArrayList<>();
    trackers.add(10L);
    trackers.add(3L);
    trackers.add(5L);
    Filter f = this.filterFactory.createTrackerFilter(trackers);
    f.serialize(uriBuilder);
    verify(uriBuilder).addParameter("tracker_id", "10|3|5");
  }

  @Test
  public void createCustomFieldFilterWithLong() {
    Filter f = this.filterFactory.createCustomFieldFilter(1L, 10l);
    f.serialize(uriBuilder);
    verify(uriBuilder).addParameter("cf_1", "10");
  }

  @Test
  public void createCustomFieldFilterWithString() {
    Filter f = this.filterFactory.createCustomFieldFilter(1L, "azertyuiop");
    f.serialize(uriBuilder);
    verify(uriBuilder).addParameter("cf_1", "azertyuiop");
  }

}
