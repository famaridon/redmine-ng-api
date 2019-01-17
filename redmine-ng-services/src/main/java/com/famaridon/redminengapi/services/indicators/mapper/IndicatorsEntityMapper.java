package com.famaridon.redminengapi.services.indicators.mapper;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper( nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IndicatorsEntityMapper {

	public Objective objectiveEntityToObjective(ObjectiveEntity objectiveEntity);
	public List<Objective> objectiveEntitiesToObjectives(Iterable<ObjectiveEntity> objectiveEntities);
	public ObjectiveEntity objectiveToObjectiveEntity(Objective objective);
	public List<ObjectiveEntity> objectiveToObjectiveEntities(Iterable<Objective> objectives);
	public void updateObjectiveEntityFromObjective(Objective objective, @MappingTarget ObjectiveEntity objectiveEntity);
	public Iteration iterationEntityToIteration(IterationEntity entity);
	public List<Iteration> iterationEntitiesToIteration(Iterable<IterationEntity> entities);
	public IterationEntity iterationToIterationEntity(Iteration iteration);
	public void updateIterationEntityFromIteration(Iteration iteration, @MappingTarget IterationEntity iterationEntity);
	public List<IterationEntity> iterationsToIterationEntities(List<Iteration> iterations);
}
