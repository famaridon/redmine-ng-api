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
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@RequestScoped
public class StatusEndpointImpl extends AbstractRedmineEndpoint implements StatusEndpoint {
	
	@EJB
	private StatusService statusService;
	
	@Inject
	private DtoMapper mapper;
	
	@Override
	public Response findAll(String apiKey) throws IOException {
		return Response.ok(StatusEndpointImpl.class.getResourceAsStream("/status/status.json")).build();
	}
	
	@Override
	public PageDto<StatusDto> findAvailableByTracker(String apiKey, Long trackerId) throws IOException {
		return this.findAvailableByTrackerAndStatus(apiKey, trackerId, null);
	}
	
	@Override
	public PageDto<StatusDto> findAvailableByTrackerAndStatus(String apiKey, Long trackerId, Long status) throws IOException {
		PageDto<StatusDto> pageDto = new PageDto<>();
		
		List<Status> statusList = this.statusService.findAvailbaleByTrackerAndStatus(apiKey,trackerId,status);
		
		pageDto.setLimit(statusList.size());
		pageDto.setOffset(0);
		pageDto.setTotalCount(statusList.size());
		
		pageDto.setElements(this.mapper.statusesToStatusDtos(statusList));
		
		return pageDto;
	}
}