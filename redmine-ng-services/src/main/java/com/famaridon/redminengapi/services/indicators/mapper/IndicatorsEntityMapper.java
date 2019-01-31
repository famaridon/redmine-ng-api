package com.famaridon.redminengapi.services.indicators.mapper;

import com.famaridon.redminengapi.domain.entities.BurndownChartEntity;
import com.famaridon.redminengapi.domain.entities.ChartTimedValueEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.services.indicators.beans.BurndownChart;
import com.famaridon.redminengapi.services.indicators.beans.ChartTimedValue;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper( nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IndicatorsEntityMapper {

	public Objective objectiveEntityToObjective(ObjectiveEntity objectiveEntity);
	public ObjectiveEntity objectiveToObjectiveEntity(Objective objective);
	public void updateObjectiveEntityFromObjective(Objective objective, @MappingTarget ObjectiveEntity objectiveEntity);

	public Iteration iterationEntityToIteration(IterationEntity entity);
	public IterationEntity iterationToIterationEntity(Iteration iteration);
	public void updateIterationEntityFromIteration(Iteration iteration, @MappingTarget IterationEntity iterationEntity);

	public BurndownChart burndownChartEntityToBurndownChart(BurndownChartEntity burndownChartEntity);
	public BurndownChartEntity burndownChartToBurndownChartEntity(BurndownChart burndownChart);
	public void updateBurndownChartEntityFromBurndownChart(BurndownChart burndownChart, @MappingTarget BurndownChartEntity burndownChartEntity);

	public ChartTimedValue chartTimedValueEntityToChartTimedValue(ChartTimedValueEntity chartTimedValueEntity);
	public ChartTimedValueEntity chartTimedValueToChartTimedValueEntity(ChartTimedValue chartTimedValue);
	public void updateChartTimedValueEntityFromChartTimedValue(ChartTimedValue chartTimedValue, @MappingTarget ChartTimedValueEntity chartTimedValueEntity);
}
