package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.IterationEndpoint;
import com.famaridon.redminengapi.rest.dto.IterationDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.exceptions.ObjectNotFoundException;
import com.famaridon.redminengapi.services.indicators.IterationService;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;

import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class IterationEndpointImpl extends AbstractRedmineEndpoint implements IterationEndpoint {
	
	@Inject
	private IterationService iterationService;
	
	@Inject
	private DtoMapper mapper;
	
	@Override
	public PageDto<IterationDto> findAll(Long offset, Long limit) throws IOException {
		Page<Iteration> iterations = iterationService.findAll(new Pager(offset, limit));
		PageDto<IterationDto> pageDto = this.mapper.pageToPageDto(iterations);
		pageDto.setElements(mapper.iterationsToIterationDtos(iterations.getElements()));
		return pageDto;
	}
	
	@Override
	public IterationDto create(IterationDto iterationDto) {
		Iteration iteration = mapper.iterationDtoToIteration(iterationDto);
		iteration = iterationService.create(iteration);
		return mapper.iterationToIterationDto(iteration);
	}
	
	@Override
	public IterationDto findCurrent() {
		Optional<Iteration> optionalIteration = this.iterationService.findCurrent();
		if(!optionalIteration.isPresent()) {
			throw new NotFoundException("No current iteration found");
		}
		return this.mapper.iterationToIterationDto(optionalIteration.get());
	}
	
	@Override
	public IterationDto findById(Long id) {
		Optional<Iteration> optionalIteration = this.iterationService.findById(id);
		if(!optionalIteration.isPresent()) {
			throw new NotFoundException("No iteration found for id " + id);
		}
		return this.mapper.iterationToIterationDto(optionalIteration.get());
	}
	
	@Override
	public void update( Long id, IterationDto iterationDto) {
		Iteration iteration = mapper.iterationDtoToIteration(iterationDto);
		iteration.setId(id);
		try {
			this.iterationService.update(iteration);
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException(e);
		}
	}
	
}
