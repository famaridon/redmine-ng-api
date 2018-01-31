package com.famaridon.redminengapi.services.redmine.mapper;

import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EntityMapper {
	public Status statusEntityToStatus(StatusEntity statusEntity);
	public List<Status> statusEntitiesToStatus(List<StatusEntity> statusEntities);
}
