package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.domain.entities.TrackerEntity;
import com.famaridon.redminengapi.domain.entities.WorkflowEntity;
import com.famaridon.redminengapi.domain.repositories.StatusRepository;
import com.famaridon.redminengapi.domain.repositories.TrackerRepository;
import com.famaridon.redminengapi.domain.repositories.WorkflowRepository;
import com.famaridon.redminengapi.services.redmine.StatusService;
import com.famaridon.redminengapi.services.redmine.mapper.EntityMapper;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Stateless
public class DefaultStatusService extends AbstractRedmineService<Status> implements StatusService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultStatusService.class);
	
	@EJB
	private TrackerRepository trackerRepository;
	
	@EJB
	private StatusRepository statusRepository;
	
	@EJB
	private WorkflowRepository workflowRepository;
	
	@Inject
	private EntityMapper entityMapper;

	@Override
	public Set<Status> findAvailbaleByTrackerForNew(String apiKey, Long tracker) throws IOException {
		Optional<TrackerEntity> trackerEntity = this.trackerRepository.findByExternalId(tracker);
		trackerEntity.orElseThrow(RuntimeException::new);
		
		Set<StatusEntity> result = new HashSet<>();
		this.workflowRepository.findByTrackerAndStatus(trackerEntity.get(), Optional.empty()).ifPresent(workflowEntity -> {
			result.addAll(workflowEntity.getAvailableStatusList());
		});
		return this.entityMapper.statusEntitiesToStatus(result);
	}
	
	@Override
	public Set<Status> findAvailbaleByTrackerAndStatus(String apiKey, Long tracker, Long status) throws IOException {
		Optional<TrackerEntity> trackerEntity = this.trackerRepository.findByExternalId(tracker);
		trackerEntity.orElseThrow(RuntimeException::new);
		
		Optional<StatusEntity> statusEntity = this.statusRepository.findByExternalId(status);
		statusEntity.orElseThrow(RuntimeException::new);
		
		Set<StatusEntity> result = new HashSet<>();
		Optional<WorkflowEntity> workflowEntity = this.workflowRepository.findByTrackerAndStatus(trackerEntity.get(), statusEntity);
		workflowEntity.ifPresent(we -> {
			result.addAll(we.getAvailableStatusList());
		});
		return this.entityMapper.statusEntitiesToStatus(result);
	}
}
