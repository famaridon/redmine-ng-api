package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Query;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;

import java.util.List;

public interface FilterFactory
{
  public Filter createProjectFilter(Project project);
  public Filter createProjectFilter(Long projectId);
  public Filter createQueryFilter(Query query);
  public Filter createQueryFilter(Long queryId);
  public Filter createStatusFilter(Status status);
  public Filter createStatusFilter(Long statusId);
  public Filter createStatusFilter(StatusType statusType);
  public Filter createTrackerFilter(Long trackerId);
  public Filter createTrackerFilter(List<Long> trackersId) ;
  public Filter createCustomFieldFilter(Long fieldId, Long value);
  public Filter createCustomFieldFilter(Long fieldId, String value);

}
