package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.ApoEndpoint;
import com.famaridon.redminengapi.services.redmine.Filter;
import com.famaridon.redminengapi.services.redmine.FilterFactory;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


@RequestScoped
public class ApoEndpointImpl implements ApoEndpoint {

  @Inject
  IssueService issueService;
  @Inject
  private FilterFactory filterFactory;

  @Override
  public String test() {
    String apiKey = "005556de9c58b855dd32042afb8955858eb02c01";
    Long id = 62409l;
    Issue issue = null;
    String result = "{\n \t \"issues\": [\n";
    Pager page = new Pager(0L, 10L);
    Page<Issue> listIssue;
    listIssue = new Page<>();
    List<Filter> filter;
    filter = new ArrayList<>();
    filter.add(this.filterFactory.createStatusFilter(5L));
    filter.add(this.filterFactory.createProjectFilter(378L));
    filter.add(this.filterFactory.createCustomFieldFilter(35L, 54L));

    try {
      /*issue = issueService.findById(apiKey,id);*/
      listIssue = issueService.findAllByFilters(apiKey, filter, page);
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (int i = 0; i < 10; i++) {
      result = result + "\t\t{\n \t\t\t\"id\" : " + listIssue.getElements().get(i).getId() + ", \n \t\t\t\"subject\" : \"" + listIssue
          .getElements().get(i).getSubject() + "\"\n\t\t},\n";
    }
    result = result + "\t]\n}";
    return result;

  }
}
