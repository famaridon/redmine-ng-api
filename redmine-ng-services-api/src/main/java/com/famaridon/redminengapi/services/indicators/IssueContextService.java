package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.exceptions.ObjectNotFoundException;
import com.famaridon.redminengapi.services.indicators.beans.IssueContext;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;

public interface IssueContextService extends CrudService<IssueContext> {

  Page<IssueContext> findAllByScopeId(Long scopeId, Pager pager)
      throws ObjectNotFoundException;

}
