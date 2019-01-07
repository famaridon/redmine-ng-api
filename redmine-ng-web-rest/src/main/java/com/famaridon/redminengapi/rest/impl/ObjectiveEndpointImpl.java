package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.ObjectiveEndpoint;
import com.famaridon.redminengapi.rest.api.QueriesEndpoint;
import com.famaridon.redminengapi.rest.dto.ObjectiveDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.QueryDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.indicators.ObjectiveService;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.QueryService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Query;

import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;
import javax.ws.rs.NotFoundException;

@RequestScoped
public class ObjectiveEndpointImpl extends AbstractRedmineEndpoint implements ObjectiveEndpoint {

	@Inject
	private DtoMapper mapper;
	
	@Inject
	private ObjectiveService objectiveService;


	@Override
	public PageDto<ObjectiveDto> findAll(String apiKey) {
		return this.mapper.pageToPageDto(this.objectiveService.findAll(new Pager()));
	}
	
	@Override
	public ObjectiveDto create(ObjectiveDto objectiveDto)
	{
		Objective objective = this.mapper.objectiveDtoToObjective(objectiveDto);
		objective = this.objectiveService.create(objective);
		return this.mapper.objectiveToObjectiveDto(objective);
	}
	
	@Override
	public ObjectiveDto findById(Long id)
	{
		Optional<Objective> optionalObjective = this.objectiveService.findById(id);
		if(!optionalObjective.isPresent()) {
			throw new NotFoundException("No Objective found for id " + id);
		}
		return this.mapper.objectiveToObjectiveDto(optionalObjective.get());
	}
	
	
	
}