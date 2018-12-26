package com.famaridon.redminengapi.services.indicators.mapper;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.entities.PriorityEntity;
import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Priority;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;
import java.util.Set;

@Mapper( nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
public interface IndicatorsEntityMapper {

	public Objective objectiveEntityToObjective(ObjectiveEntity objectiveEntity);
	public List<Objective> objectiveEntitiesToObjectives(Iterable<ObjectiveEntity> objectiveEntities);
	public Iteration iterationEntityToIteration(IterationEntity entity);
	public List<Iteration> iterationEntitiesToIteration(Iterable<IterationEntity> entities);
	public IterationEntity iterationToIterationEntity(Iteration iteration);
	public void updateIterationEntityFromIteration(Iteration iteration, @MappingTarget IterationEntity iterationEntity);
	public List<IterationEntity> iterationsToIterationEntities(List<Iteration> iterations);
}
