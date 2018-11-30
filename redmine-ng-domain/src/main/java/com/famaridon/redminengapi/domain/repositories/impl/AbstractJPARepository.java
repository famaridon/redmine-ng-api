package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.AbstractEntity;
import com.famaridon.redminengapi.domain.repositories.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class AbstractJPARepository<T extends AbstractEntity> implements Repository<T> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractJPARepository.class);

    @PersistenceContext(name = "redmine-domain")
    protected EntityManager em;

    public T findById(Long id) {
        return this.em.find(this.getClazz(), id);
    }

    public T save(T entity) {
        boolean isCreation = false;
        if (entity.getId() == null) {
            isCreation = true;
        }
        T result = this.em.merge(entity);
        if (isCreation) {
            LOG.info("Create entity : {} ", result);
        } else {
            LOG.info("Update entity : {} ", result);
        }
        return result;
    }

    @Override
    public Iterable<T> findAll() {
        return this.findAll(0L, 50L);
    }

    @Override
    public Iterable<T> findAll(Long offset, Long limit) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(this.getClazz());
        Root<T> c = q.from(this.getClazz());
        q.select(c);
        TypedQuery<T> query = this.em.createQuery(q);
        query.setFirstResult(Math.toIntExact(offset));
        query.setMaxResults(Math.toIntExact(limit));
        return query.getResultList();
    }

    protected abstract Class<T> getClazz();

}
