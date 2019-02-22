package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.redmine.Filter;
import com.famaridon.redminengapi.services.redmine.FilterFactory;
import com.famaridon.redminengapi.services.redmine.StatusType;
import com.famaridon.redminengapi.services.redmine.filter.LongFilter;
import com.famaridon.redminengapi.services.redmine.filter.StringFilter;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Query;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.inject.Default;
import javax.inject.Named;

@Named
@Default
public class DefaultFilterFactory implements FilterFactory {

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

  @Override
  public Filter createStatusFilter(List<Long> statusIds) {
    return new StringFilter("status_id", statusIds.stream().map(Object::toString).collect(Collectors.joining("|")));
  }

  public Filter createStatusFilter(Long statusId) {
    return new LongFilter("status_id", statusId);
  }

  public Filter createStatusFilter(StatusType statusType) {
    return new StringFilter("status_id", statusType.filterValue);
  }

  public Filter createTrackerFilter(Long trackerId) {
    return new LongFilter("tracker_id", trackerId);
  }

  public Filter createTrackerFilter(List<Long> trackersId) {
    return new StringFilter("tracker_id", trackersId.stream().map(Object::toString).collect(Collectors.joining("|")));
  }

  public Filter createCustomFieldFilter(Long fieldId, Long value) {
    return new LongFilter("cf_" + fieldId, value);
  }

  public Filter createCustomFieldFilter(Long fieldId, String value) {
    return new StringFilter("cf_" + fieldId, value);
  }

  @Override
  public Filter createVersionFilter(Long versionId) {
    return new LongFilter("fixed_version_id" , versionId);
  }


  @Override
  public Filter createCategoryFilter(Long categoryId) {
    return new LongFilter("category_id", categoryId);
  }

}
