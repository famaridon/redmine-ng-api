package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;

public interface ObjectiveRepository extends Repository<ObjectiveEntity> {

  Iterable<ObjectiveEntity> findAllByIteration(IterationEntity iterationEntity, Long offset,
      Long limit);

}
