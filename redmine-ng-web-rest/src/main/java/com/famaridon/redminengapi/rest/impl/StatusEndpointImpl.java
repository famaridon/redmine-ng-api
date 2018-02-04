package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.StatusEndpoint;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.StatusDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.redmine.StatusService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

@RequestScoped
public class StatusEndpointImpl extends AbstractRedmineEndpoint implements StatusEndpoint {
	
	@EJB
	private StatusService statusService;
	
	@Inject
	private DtoMapper mapper;
	
	@Override
	public PageDto<StatusDto> findAvailableByTracker(String apiKey, Long trackerId) throws IOException {
		Set<Status> statusSet = this.statusService.findAvailbaleByTrackerForNew(apiKey,trackerId);
		return this.mapToPage(statusSet);
	}
	
	@Override
	public PageDto<StatusDto> findAvailableByTrackerAndStatus(String apiKey, Long trackerId, Long status) throws IOException {
		Set<Status> statusSet = this.statusService.findAvailbaleByTrackerAndStatus(apiKey,trackerId,status);
		return this.mapToPage(statusSet);
	}
	
	private PageDto<StatusDto> mapToPage(Collection<Status> statuses){
		PageDto<StatusDto> pageDto = new PageDto<>();
		pageDto.setLimit(statuses.size());
		pageDto.setOffset(0);
		pageDto.setTotalCount(statuses.size());
		pageDto.setElements(this.mapper.statusesToStatusDtos(statuses));
		return pageDto;
	}
}