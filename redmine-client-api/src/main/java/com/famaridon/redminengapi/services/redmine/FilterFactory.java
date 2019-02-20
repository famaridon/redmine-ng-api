package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Query;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;

import java.util.List;

public interface FilterFactory
{
  Filter createProjectFilter(Project project);
  Filter createProjectFilter(Long projectId);
  Filter createQueryFilter(Query query);
  Filter createQueryFilter(Long queryId);
  Filter createStatusFilter(Status status);
  Filter createStatusFilter(List<Long> statusIds);
  Filter createStatusFilter(Long statusId);
  Filter createStatusFilter(StatusType statusType);
  Filter createTrackerFilter(Long trackerId);
  Filter createTrackerFilter(List<Long> trackersId) ;
  Filter createCustomFieldFilter(Long fieldId, Long value);
  Filter createCustomFieldFilter(Long fieldId, String value);
  Filter createCategoryFilter(Long categoryId);
}
