package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.BurndownChartEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import java.util.Optional;

public interface BurndownChartRepository extends Repository<BurndownChartEntity> {

  public Optional<BurndownChartEntity> findByIteration(IterationEntity iterationEntity);
  public Optional<BurndownChartEntity> findCurrent();

}
