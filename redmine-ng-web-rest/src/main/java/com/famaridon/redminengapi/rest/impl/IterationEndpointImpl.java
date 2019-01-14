package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.IterationEndpoint;
import com.famaridon.redminengapi.rest.dto.IterationDto;
import com.famaridon.redminengapi.services.indicators.IterationService;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@RequestScoped
public class IterationEndpointImpl extends AbstractCrudEndpoint<IterationDto, IterationService, Iteration> implements IterationEndpoint {
	
	@Inject
	private IterationService iterationService;


	@Override
	protected IterationService getService() {
		return this.iterationService;
	}

	@Override
	protected Iteration dtoToBean(IterationDto dto) {
		return this.mapper.iterationDtoToIteration(dto);
	}

	@Override
	protected IterationDto beanToDto(Iteration bean) {
		return this.mapper.iterationToIterationDto(bean);
	}

	@Override
	public IterationDto findCurrent() {
		Optional<Iteration> optionalIteration = this.iterationService.findCurrent();
		if(!optionalIteration.isPresent()) {
			throw new NotFoundException("No current iteration found");
		}
		return this.mapper.iterationToIterationDto(optionalIteration.get());
	}
}
