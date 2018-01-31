package com.famaridon.redminengapi.domain.repositories;

import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.domain.entities.TrackerEntity;
import com.famaridon.redminengapi.domain.entities.WorkflowEntity;

public interface WorkflowRepository extends Repository<WorkflowEntity> {
	
	public WorkflowEntity findByTrackerIdAndStatusId(Long trackerId,  Long statusId);
	public WorkflowEntity findByTrackerAndStatus(TrackerEntity tracker,  StatusEntity status);

}
