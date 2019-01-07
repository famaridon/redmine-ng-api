package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.services.indicators.IterationService;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

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
	public Optional<Iteration> findById(Long id) {
		Optional<IterationEntity> entityOptional = this.iterationRepository.findById(id);
		return entityOptional.map(iterationEntity -> this.indicatorsEntityMapper.iterationEntityToIteration(iterationEntity));
	}
	
	@Override
	public void update(Iteration iteration) {
		Optional<IterationEntity> entityOptional = this.iterationRepository.findById(iteration.getId());
		if(entityOptional.isPresent()) {
			IterationEntity entity = entityOptional.get();
			this.indicatorsEntityMapper.updateIterationEntityFromIteration(iteration,entity);
			this.iterationRepository.save(entity);
		} else {
			throw new NotFoundException();
		}
	}
	
	@Override
	public Optional<Iteration> findCurrent() {
		Optional<IterationEntity> entityOptional = this.iterationRepository.findCurrentIteration();
		return entityOptional.map(iterationEntity -> this.indicatorsEntityMapper.iterationEntityToIteration(iterationEntity));
	}
}
