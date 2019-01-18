package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.ObjectiveEndpoint;
import com.famaridon.redminengapi.rest.api.QueriesEndpoint;
import com.famaridon.redminengapi.rest.dto.ObjectiveDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.QueryDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.exceptions.ObjectNotFoundException;
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
public class ObjectiveEndpointImpl extends AbstractCrudEndpoint<ObjectiveDto, ObjectiveService, Objective> implements ObjectiveEndpoint {
	
	@Inject
	private ObjectiveService objectiveService;

	@Override
	public PageDto<ObjectiveDto> findAllByIterationId(Long iterationId, Long offset, Long limit) {
		try {
			Page<Objective> page = this.objectiveService.findAllByIterationId(iterationId, new Pager(offset,limit));
			PageDto<ObjectiveDto> pageDto = this.mapper.pageToPageDto(page);
			pageDto.setElements(this.mapper.objectivesToObjectiveDtos(page.getElements()));
			return pageDto;
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException(e.getMessage(),e);
		}
	}

	@Override
	protected ObjectiveService getService() {
		return this.objectiveService;
	}

	@Override
	protected Objective dtoToBean(ObjectiveDto dto) {
		return this.mapper.objectiveDtoToObjective(dto);
	}

	@Override
	protected ObjectiveDto beanToDto(Objective bean) {
		return this.mapper.objectiveToObjectiveDto(bean);
	}


}