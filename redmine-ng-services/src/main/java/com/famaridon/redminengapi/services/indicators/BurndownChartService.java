package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.exceptions.ObjectNotFoundException;
import com.famaridon.redminengapi.services.indicators.beans.BurndownChart;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.io.IOException;
import java.util.Optional;

public interface BurndownChartService extends CrudService<BurndownChart> {

  Optional<BurndownChart> findByIteration(Long iterationId) throws ObjectNotFoundException;
  BurndownChart buildIdealIteration(Long iterationId) throws ObjectNotFoundException, IOException;

}
