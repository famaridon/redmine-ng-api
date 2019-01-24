package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.filter.LongFilter;
import com.famaridon.redminengapi.services.redmine.filter.StringFilter;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Query;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;

public class FilterFactory {

  public Filter createProjectFilter(Project project) {
    return this.createProjectFilter(project.getId());
  }

  public Filter createProjectFilter(Long projectId) {
    return new LongFilter("project_id", projectId);
  }

  public Filter createQueryFilter(Query query) {
    return this.createQueryFilter(query.getId());
  }

  public Filter createQueryFilter(Long queryId) {
    return new LongFilter("query_id", queryId);
  }

  public Filter createStatusFilter(Status status) {
    return this.createStatusFilter(status.getId());
  }

  public Filter createStatusFilter(Long statusId) {
    return new LongFilter("status_id", statusId);
  }

  public Filter createStatusFilter(StatusType statusType) {
    return new StringFilter("status_id", statusType.filterValue);
  }

}
