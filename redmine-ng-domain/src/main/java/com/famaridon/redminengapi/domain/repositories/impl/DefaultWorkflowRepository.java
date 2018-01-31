package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.domain.entities.TrackerEntity;
import com.famaridon.redminengapi.domain.entities.WorkflowEntity;
import com.famaridon.redminengapi.domain.repositories.WorkflowRepository;

import javax.ejb.Stateful;
import javax.persistence.TypedQuery;

@Stateful
public class DefaultWorkflowRepository extends AbstractRepository<WorkflowEntity> implements WorkflowRepository {
	
	public WorkflowEntity findByTrackerIdAndStatusId(Long trackerId,  Long statusId){
		TypedQuery<WorkflowEntity> query = em.createNamedQuery("WorkflowEntity.findByTrackerIdAndStatusId", WorkflowEntity.class);
		query.setParameter("tracker", trackerId);
		query.setParameter("status", statusId);
		return query.getSingleResult();
	}
	public WorkflowEntity findByTrackerAndStatus(TrackerEntity tracker,  StatusEntity status){
		TypedQuery<WorkflowEntity> query = em.createNamedQuery("WorkflowEntity.findByTrackerAndStatus", WorkflowEntity.class);
		query.setParameter("tracker", tracker);
		query.setParameter("status", status);
		return query.getSingleResult();
	}
	
	@Override
	protected Class<WorkflowEntity> getClazz() {
		return WorkflowEntity.class;
	}
}
