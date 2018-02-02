package com.famaridon.redminengapi.services.redmine.mapper;

import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface EntityMapper {
	public Status statusEntityToStatus(StatusEntity statusEntity);
	public Set<Status> statusEntitiesToStatus(Set<StatusEntity> statusEntities);
}
