package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.domain.entities.IssueContextEntity;
import com.famaridon.redminengapi.domain.entities.IssueScopeEntity;
import com.famaridon.redminengapi.domain.repositories.IssueContextRepository;
import com.famaridon.redminengapi.domain.repositories.IssueScopeRepository;
import com.famaridon.redminengapi.services.exceptions.ObjectNotFoundException;
import com.famaridon.redminengapi.services.indicators.IssueContextService;
import com.famaridon.redminengapi.services.indicators.beans.IssueContext;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapper;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapperImpl;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DefaultIssueContextService extends AbstractCrudRepositoryService<IssueContextRepository, IssueContextEntity, IssueContext> implements
    IssueContextService {

  @Inject
  private IssueContextRepository issueContextRepository;
  @Inject
  private IndicatorsEntityMapper indicatorsEntityMapper;
  @Inject
  private IssueScopeRepository issueScopeRepository;

  public DefaultIssueContextService() {
  }

  public DefaultIssueContextService(IssueContextRepository issueContextRepository, IssueScopeRepository issueScopeRepository) {
    this.issueContextRepository = issueContextRepository;
    this.issueScopeRepository = issueScopeRepository;
    this.indicatorsEntityMapper = new IndicatorsEntityMapperImpl();
  }

  @Override
  protected IssueContextRepository getRepository() {
    return this.issueContextRepository;
  }

  @Override
  protected IssueContext map(IssueContextEntity entity) {
    return this.indicatorsEntityMapper.issueContextEntityToIssueContext(entity);
  }

  @Override
  protected IssueContextEntity map(IssueContext bean) {
    return this.indicatorsEntityMapper.issueContextToIssueContextEntity(bean);
  }

  @Override
  protected void merge(IssueContext bean, IssueContextEntity entity) {
    this.indicatorsEntityMapper.updateIssueContextEntityFromIssueContext(bean, entity);
  }

  @Override
  public Page<IssueContext> findAllByScopeId(Long scopeId, Pager pager) throws ObjectNotFoundException {
    Optional<IssueScopeEntity> issueScopeEntity = this.issueScopeRepository.findById(scopeId);
    if (!issueScopeEntity.isPresent()) {
      throw new ObjectNotFoundException("No scope found for id " + scopeId);
    }
    Iterable<IssueContextEntity> issueContextEntities = this.issueContextRepository
        .findAllByScope(issueScopeEntity.get(), pager.getOffset(), pager.getLimit());
    return this.toPage(issueContextEntities, pager);
  }
}
