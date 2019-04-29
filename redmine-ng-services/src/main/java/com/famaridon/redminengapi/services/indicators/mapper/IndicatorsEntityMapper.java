package com.famaridon.redminengapi.services.indicators.mapper;

import com.famaridon.redminengapi.domain.entities.BurndownChartEntity;
import com.famaridon.redminengapi.domain.entities.ChartTimedValueEntity;
import com.famaridon.redminengapi.domain.entities.IssueContextEntity;
import com.famaridon.redminengapi.domain.entities.IssueScopeEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.services.indicators.beans.BurndownChart;
import com.famaridon.redminengapi.services.indicators.beans.ChartTimedValue;
import com.famaridon.redminengapi.services.indicators.beans.IssueContext;
import com.famaridon.redminengapi.services.indicators.beans.IssueScope;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IndicatorsEntityMapper {

  Objective objectiveEntityToObjective(ObjectiveEntity objectiveEntity);

  ObjectiveEntity objectiveToObjectiveEntity(Objective objective);

  void updateObjectiveEntityFromObjective(Objective objective, @MappingTarget ObjectiveEntity objectiveEntity);

  Iteration iterationEntityToIteration(IterationEntity entity);

  IterationEntity iterationToIterationEntity(Iteration iteration);

  void updateIterationEntityFromIteration(Iteration iteration, @MappingTarget IterationEntity iterationEntity);

  BurndownChart burndownChartEntityToBurndownChart(BurndownChartEntity burndownChartEntity);

  BurndownChartEntity burndownChartToBurndownChartEntity(BurndownChart burndownChart);

  void updateBurndownChartEntityFromBurndownChart(BurndownChart burndownChart, @MappingTarget BurndownChartEntity burndownChartEntity);

  ChartTimedValue chartTimedValueEntityToChartTimedValue(ChartTimedValueEntity chartTimedValueEntity);

  ChartTimedValueEntity chartTimedValueToChartTimedValueEntity(ChartTimedValue chartTimedValue);

  void updateChartTimedValueEntityFromChartTimedValue(ChartTimedValue chartTimedValue,
      @MappingTarget ChartTimedValueEntity chartTimedValueEntity);

  IssueContext issueContextEntityToIssueContext(IssueContextEntity issueContextEntity);

  IssueContextEntity issueContextToIssueContextEntity(IssueContext issueContext);

  void updateIssueContextEntityFromIssueContext(IssueContext issueContext, @MappingTarget IssueContextEntity issueContextEntity);

  IssueScope issueScopeEntityToIssueScope(IssueScopeEntity issueScopeEntity);

  IssueScopeEntity issueScopeToIssueScopeEntity(IssueScope issueScope);

  void updateIssueScopeEntityFromIssueScope(IssueScope issueScope, @MappingTarget IssueScopeEntity issueScopeEntity);
}
