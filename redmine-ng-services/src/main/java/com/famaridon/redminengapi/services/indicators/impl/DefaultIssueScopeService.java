package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.domain.entities.IssueScopeEntity;
import com.famaridon.redminengapi.domain.repositories.IssueScopeRepository;
import com.famaridon.redminengapi.services.indicators.IssueScopeService;
import com.famaridon.redminengapi.services.indicators.beans.IssueScope;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapper;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapperImpl;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DefaultIssueScopeService extends AbstractCrudRepositoryService<IssueScopeRepository, IssueScopeEntity, IssueScope> implements
    IssueScopeService {

  @Inject
  protected IssueScopeRepository issueScopeRepository;
  @Inject
  private IndicatorsEntityMapper indicatorsEntityMapper;

  public DefaultIssueScopeService() {
  }

  public DefaultIssueScopeService(IssueScopeRepository issueScopeRepository) {
    this.issueScopeRepository = issueScopeRepository;
    this.indicatorsEntityMapper = new IndicatorsEntityMapperImpl();
  }

  @Override
  protected IssueScopeRepository getRepository() {
    return this.issueScopeRepository;
  }

  @Override
  protected IssueScope map(IssueScopeEntity entity) {
    return this.indicatorsEntityMapper.issueScopeEntityToIssueScope(entity);
  }

  @Override
  protected IssueScopeEntity map(IssueScope bean) {
    return this.indicatorsEntityMapper.issueScopeToIssueScopeEntity(bean);
  }

  @Override
  protected void merge(IssueScope bean, IssueScopeEntity entity) {
    this.indicatorsEntityMapper.updateIssueScopeEntityFromIssueScope(bean, entity);
  }
}
