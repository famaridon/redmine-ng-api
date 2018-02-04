package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.domain.entities.TrackerEntity;
import com.famaridon.redminengapi.domain.entities.WorkflowEntity;

import java.util.Optional;

public interface WorkflowRepository extends Repository<WorkflowEntity> {
	
	public Optional<WorkflowEntity> findByTrackerAndStatus(TrackerEntity tracker,  Optional<StatusEntity> status);

}
