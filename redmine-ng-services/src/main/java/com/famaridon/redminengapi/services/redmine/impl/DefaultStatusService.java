package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.domain.entities.WorkflowEntity;
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
import java.util.List;

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
	public List<Status> findAvailbaleByTracker(String apiKey, Long tracker) throws IOException {
		WorkflowEntity workflowEntity = workflowRepository.findByTrackerIdAndStatusId(tracker, null);
		return this.entityMapper.statusEntitiesToStatus(workflowEntity.getAvailableStatusList());
	}
	
	@Override
	public List<Status> findAvailbaleByTrackerAndStatus(String apiKey, Long tracker, Long status) throws IOException {
		WorkflowEntity workflowEntity = workflowRepository.findByTrackerIdAndStatusId(tracker, status);
		return this.entityMapper.statusEntitiesToStatus(workflowEntity.getAvailableStatusList());
	}
}
