package com.famaridon.redminengapi.services.redmine.mapper;

import com.famaridon.redminengapi.domain.entities.PriorityEntity;
import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Priority;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Set;

@Mapper
public interface RedmineEntityMapper {
	
	
	@Mappings({ @Mapping(target = "id", source = "externalId") })
	public Priority priorityEntityToPriority(PriorityEntity priorityEntity);
	
	public Status statusEntityToStatus(StatusEntity statusEntity);
	
	public Set<Status> statusEntitiesToStatus(Set<StatusEntity> statusEntities);
}
