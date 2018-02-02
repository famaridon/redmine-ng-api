package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.domain.entities.TrackerEntity;
import com.famaridon.redminengapi.domain.entities.WorkflowEntity;
import com.famaridon.redminengapi.domain.repositories.WorkflowRepository;

import javax.ejb.Stateful;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Stateful
public class DefaultWorkflowRepository extends AbstractRepository<WorkflowEntity> implements WorkflowRepository {
	
	public Optional<WorkflowEntity> findByTrackerIdAndStatusId(Long trackerId,  Long statusId){
		TypedQuery<WorkflowEntity> query = em.createNamedQuery("WorkflowEntity.findByTrackerIdAndStatusId", WorkflowEntity.class);
		query.setParameter("tracker", trackerId);
		query.setParameter("status", statusId);
		WorkflowEntity result = null;
		try{
			result = query.getSingleResult();
		} catch (NoResultException e){
			// nothing to do;
		}
		return Optional.ofNullable(result);
	}
	
	public Optional<WorkflowEntity> findByTrackerAndStatus(TrackerEntity tracker,  StatusEntity status){
		TypedQuery<WorkflowEntity> query = em.createNamedQuery("WorkflowEntity.findByTrackerAndStatus", WorkflowEntity.class);
		query.setParameter("tracker", tracker);
		query.setParameter("status", status);
		WorkflowEntity result = null;
		try{
			result = query.getSingleResult();
		} catch (NoResultException e){
			// nothing to do;
		}
		return Optional.ofNullable(result);
	}
	
	@Override
	protected Class<WorkflowEntity> getClazz() {
		return WorkflowEntity.class;
	}
}
