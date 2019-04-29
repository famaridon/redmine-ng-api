package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.IssueContextEntity;
import com.famaridon.redminengapi.domain.entities.IssueScopeEntity;
import com.famaridon.redminengapi.domain.repositories.IssueContextRepository;
import javax.ejb.Stateful;
import javax.persistence.TypedQuery;

@Stateful
public class JPAIssueContextRepository extends AbstractJPARepository<IssueContextEntity> implements IssueContextRepository {

  @Override
  protected Class<IssueContextEntity> getClazz() {
    return IssueContextEntity.class;
  }

  @Override
  public Iterable<IssueContextEntity> findAllByScope(IssueScopeEntity issueScopeEntity, Long offset, Long limit) {
    TypedQuery<IssueContextEntity> typedQuery = this.em.createNamedQuery("IssueContextEntity.findAllByScope", IssueContextEntity.class);
    typedQuery.setParameter("scope", issueScopeEntity);
    typedQuery.setFirstResult(Math.toIntExact(offset));
    typedQuery.setMaxResults(Math.toIntExact(limit));
    return typedQuery.getResultList();
  }
}
