package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.repositories.ObjectiveRepository;
import com.famaridon.redminengapi.services.indicators.ObjectiveService;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapper;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class DefaultObjectiveService implements ObjectiveService {

    @Inject
    private IndicatorsEntityMapper indicatorsEntityMapper;
    @Inject
    private ObjectiveRepository objectiveRepository;

    @Override
    public Page<Objective> findAll(Pager pager) {
        Iterable<ObjectiveEntity> objectiveEntities=  this.objectiveRepository.findAll(pager.getOffset(), pager.getLimit());
        List<Objective> objectives = this.indicatorsEntityMapper.objectiveEntitiesToObjectives(objectiveEntities);
        Page<Objective> page = new Page<>();
        page.setElements(objectives);
        page.setOffset(pager.getLimit());
        page.setLimit(objectives.size());
        return page;
    }
    
    @Override
    public Objective create(Objective objective)
    {
        ObjectiveEntity objectiveEntity = indicatorsEntityMapper.objectiveToObjectiveEntity(objective);
        objectiveEntity = objectiveRepository.save(objectiveEntity);
        return indicatorsEntityMapper.objectiveEntityToObjective(objectiveEntity);
    }
    
    @Override
    public Objective findById(Long id)
    {
        ObjectiveEntity objectiveEntity = objectiveRepository.findById(id);
        return indicatorsEntityMapper.objectiveEntityToObjective(objectiveEntity);
    }
    
    @Override
    public void update(Objective objective)
    {
        ObjectiveEntity objectiveEntity = this.objectiveRepository.findById(objective.getId());
        this.indicatorsEntityMapper.updateObjectiveEntityFromObjective(objective,objectiveEntity);
        this.objectiveRepository.save(objectiveEntity);
    }
    
    @Override
    public void delete(Objective objective)
    {
        ObjectiveEntity objectiveEntity = objectiveRepository.findById(objective.getId());
        objectiveRepository.delete(objectiveEntity);
    }
    
}
