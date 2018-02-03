package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.PrioritiesEndpoint;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.PriorityDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.redmine.PriorityService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Priority;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Set;

@RequestScoped
public class PrioritiesEndpointImpl extends AbstractRedmineEndpoint implements PrioritiesEndpoint {
	
	@EJB
	private PriorityService priorityService;
	
	@Inject
	private DtoMapper mapper;
	
	@Override
	public PageDto<PriorityDto> findAll(String apiKey) throws IOException {
		PageDto<PriorityDto> pageDto = new PageDto<>();
		Set<Priority> prioritySet = this.priorityService.findAll(apiKey);
		pageDto.setTotalCount(prioritySet.size());
		pageDto.setOffset(0);
		pageDto.setLimit(prioritySet.size());
		pageDto.setElements(this.mapper.prioritiesToPriorityDtos(prioritySet));
		return pageDto;
	}
}