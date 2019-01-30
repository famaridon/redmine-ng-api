package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.services.indicators.BurndownChartService;
import com.famaridon.redminengapi.services.indicators.impl.issue.IssueOperator;
import com.famaridon.redminengapi.services.redmine.StatusType;
import java.io.IOException;

public interface InternalBurndownChartService extends BurndownChartService {
  public <T> T agregateIssues(IterationEntity iterationEntity, StatusType statusType, IssueOperator<T> issueOperator, T initial)
      throws IOException;
}
