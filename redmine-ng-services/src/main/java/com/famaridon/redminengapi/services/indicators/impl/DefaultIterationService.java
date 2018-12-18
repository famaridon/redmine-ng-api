package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.services.indicators.IterationService;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DefaultIterationService implements IterationService {
	
	@Inject
	private IndicatorsEntityMapper indicatorsEntityMapper;
	@Inject
	protected IterationRepository iterationRepository;
	
	@Override
	public List<Iteration> findAll()
	{
		Iterable<IterationEntity> iterationEntities = iterationRepository.findAll();
		return indicatorsEntityMapper.iterationEntitiesToIteration(iterationEntities);
	}
}
