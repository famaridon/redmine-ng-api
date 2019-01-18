package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.domain.entities.AbstractEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.domain.repositories.Repository;
import com.famaridon.redminengapi.services.exceptions.ObjectNotFoundException;
import com.famaridon.redminengapi.services.indicators.CrudService;
import com.famaridon.redminengapi.services.indicators.IterationService;
import com.famaridon.redminengapi.services.indicators.beans.AbstractBean;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapper;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

public abstract class AbstractCrudRepositoryService<R extends Repository<E>, E extends AbstractEntity, B extends AbstractBean> implements
    CrudService<B> {

  protected abstract R getRepository();

  protected abstract B map(E entity);

  protected abstract E map(B bean);

  protected abstract void merge(B bean, E entity);

  protected List<B> map(Iterable<E> entities) {
    List<B> result = new ArrayList<>();
    entities.forEach(entity -> result.add(this.map(entity)));
    return result;
  }

  @Override
  public Page<B> findAll(Pager pager) {
    Iterable<E> entities = this.getRepository().findAll(pager.getOffset(), pager.getLimit());
    return this.toPage(entities, pager);
  }

  protected final Page<B> toPage(Iterable<E> entities, Pager pager) {
    List<B> beans = this.map(entities);
    Page<B> page = new Page<>();
    page.setElements(beans);
    page.setOffset(pager.getLimit());
    page.setLimit(beans.size());
    return page;
  }

  @Override
  public B create(B bean) {
    E entity = this.map(bean);
    E saved = this.getRepository().save(entity);
    return this.map(saved);
  }

  @Override
  public Optional<B> findById(Long id) {
    Optional<E> optionalEntity = this.getRepository().findById(id);
    return optionalEntity.map(this::map);
  }

  @Override
  public void update(B bean) throws ObjectNotFoundException {
    Optional<E> optionalEntity = this.getRepository().findById(bean.getId());
    if (optionalEntity.isPresent()) {
      E entity = optionalEntity.get();
      this.merge(bean, entity);
      this.getRepository().save(entity);
    } else {
      throw new ObjectNotFoundException("Can't update non existing object.");
    }
  }

  @Override
  public void deleteById(Long id) throws ObjectNotFoundException {
    Optional<E> optionalEntity = this.getRepository().findById(id);
    if (optionalEntity.isPresent()) {
      E entity = optionalEntity.get();
      this.getRepository().delete(entity);
    } else {
      throw new ObjectNotFoundException("Can't delete non existing object.");
    }
  }

  @Override
  public void delete(B bean) throws ObjectNotFoundException {
    this.deleteById(bean.getId());
  }
}
