package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.IssueContextEntity;
import com.famaridon.redminengapi.domain.entities.IssueScopeEntity;

public interface IssueContextRepository extends Repository<IssueContextEntity> {

  Iterable<IssueContextEntity> findAllByScope(IssueScopeEntity issueScopeEntity, Long offset,
      Long limit);

}
