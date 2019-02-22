package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import java.util.Optional;

public interface IterationService extends CrudService<Iteration> {

  /**
   * find the current iteration.
   *
   * @return the found iteration
   */
  Optional<Iteration> findCurrent();

}
