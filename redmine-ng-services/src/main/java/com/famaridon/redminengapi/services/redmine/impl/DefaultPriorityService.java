package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.domain.entities.PriorityEntity;
import com.famaridon.redminengapi.domain.repositories.PriorityRepository;
import com.famaridon.redminengapi.services.redmine.PriorityService;
import com.famaridon.redminengapi.services.redmine.mapper.RedmineEntityMapper;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Stateless
public class DefaultPriorityService extends AbstractRedmineService<Priority> implements PriorityService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultPriorityService.class);
	
	@EJB
	private PriorityRepository priorityRepository;
	
	@Inject
	private RedmineEntityMapper entityMapper;

	@Override
	public Set<Priority> findAll(String apiKey) throws IOException {
		Iterable<PriorityEntity> priorityEntities = this.priorityRepository.findAll();
		Set<Priority> result = new HashSet<>();
		priorityEntities.forEach(p -> result.add(this.entityMapper.priorityEntityToPriority(p)));
		return result;
	}
}
