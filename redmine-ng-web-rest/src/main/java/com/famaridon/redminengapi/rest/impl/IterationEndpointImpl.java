package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.IterationEndpoint;
import com.famaridon.redminengapi.rest.dto.IterationDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.indicators.IterationService;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

@RequestScoped
public class IterationEndpointImpl extends AbstractRedmineEndpoint implements IterationEndpoint
{
	
	@Inject
	IterationService iterationService;
	
	@Inject
	private DtoMapper mapper;
	
	@Override
	public PageDto<IterationDto> findAll(String apiKey) throws IOException
	{
		List<Iteration> iterations = iterationService.findAll();
		PageDto<IterationDto> pageDto = new PageDto<>();
		List<IterationDto> iterationDtos = mapper.iterationsToIterationDtos(iterations);
		pageDto.setTotalCount(iterationDtos.size());
		pageDto.setLimit(iterationDtos.size());
		pageDto.setOffset(0);
		pageDto.setElements(iterationDtos);
		return pageDto;
	}
	
	@Override
	public IterationDto create(String apiKey, IterationDto iterationDto)
	{
		Iteration iteration = mapper.iterationDtoToIteration(iterationDto);
		iteration = iterationService.createIteration(iteration);
		return mapper.iterationToIterationDto(iteration);
	}
	
}
