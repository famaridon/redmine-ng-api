package com.famaridon.redminengapi.services.indicators.mapper;

import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.entities.PriorityEntity;
import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Priority;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper
public interface IndicatorsEntityMapper {

	public Objective objectiveEntityToObjective(ObjectiveEntity objectiveEntity);
	public List<Objective> objectiveEntitiesToObjectives(Iterable<ObjectiveEntity> objectiveEntities);
}
