package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.exceptions.ObjectNotFoundException;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;

public interface ObjectiveService extends CrudService<Objective> {

  Page<Objective> findAllByIterationId(Long iterationId, Pager pager)
      throws ObjectNotFoundException;
}
