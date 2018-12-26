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
	
	@Override
	public Iteration create(Iteration iteration)
	{
		IterationEntity iterationEntity = indicatorsEntityMapper.iterationToIterationEntity(iteration);
		iterationEntity = iterationRepository.save(iterationEntity);
		return indicatorsEntityMapper.iterationEntityToIteration(iterationEntity);
	}
	
	@Override
	public Iteration findById(Long id) {
		IterationEntity iterationEntity = this.iterationRepository.findById(id);
		return this.indicatorsEntityMapper.iterationEntityToIteration(iterationEntity);
	}
	
	@Override
	public void update(Iteration iteration) {
		IterationEntity entity = this.iterationRepository.findById(iteration.getId());
		this.indicatorsEntityMapper.updateIterationEntityFromIteration(iteration,entity);
		this.iterationRepository.save(entity);
	}
}
