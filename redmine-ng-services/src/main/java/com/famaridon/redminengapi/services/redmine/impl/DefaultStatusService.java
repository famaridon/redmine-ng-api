package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.domain.entities.StatusEntity;
import com.famaridon.redminengapi.domain.repositories.WorkflowRepository;
import com.famaridon.redminengapi.services.ConfigurationService;
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
import java.util.Set;

@Stateless
public class DefaultStatusService extends AbstractRedmineService<Status> implements StatusService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultStatusService.class);
	
	@EJB
	private WorkflowRepository workflowRepository;
	
	@Inject
	private EntityMapper entityMapper;
	
	public DefaultStatusService() {
	}
	
	public DefaultStatusService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
	@Override
	public Set<Status> findAvailbaleByTracker(String apiKey, Long tracker) throws IOException {
		Set<StatusEntity> result = new HashSet<>();
		workflowRepository.findByTrackerIdAndStatusId(tracker, null).ifPresent(workflowEntity -> {
			result.addAll(workflowEntity.getAvailableStatusList());
		});
		return this.entityMapper.statusEntitiesToStatus(result);
	}
	
	@Override
	public Set<Status> findAvailbaleByTrackerAndStatus(String apiKey, Long tracker, Long status) throws IOException {
		Set<StatusEntity> result = new HashSet<>();
		workflowRepository.findByTrackerIdAndStatusId(tracker, status).ifPresent(workflowEntity -> {
			result.addAll(workflowEntity.getAvailableStatusList());
		});
		return this.entityMapper.statusEntitiesToStatus(result);
	}
}
