package com.famaridon.redminengapi.services.indicators.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.famaridon.redminengapi.domain.entities.AbstractEntity;
import com.famaridon.redminengapi.domain.repositories.Repository;
import com.famaridon.redminengapi.services.indicators.CrudService;
import com.famaridon.redminengapi.services.indicators.beans.AbstractBean;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.util.Optional;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.WrapDynaBean;
import org.junit.Test;

public abstract class AbstractCrudServiceUTest<S extends CrudService<B>, B extends AbstractBean, R extends Repository<E>, E extends AbstractEntity> {


  protected abstract S getService();
  protected abstract R getRepository();

  @Test
  public void create() {
    B bean = this.buildBean();
    bean.setId(0L);
    bean.setName("create");

    this.getService().create(bean);

    verify(this.getRepository()).save(any());
  }

  @Test
  public void findById() {

    E entity = this.buildEntity();
    entity.setId(0L);
    entity.setName("findById");
    when(getRepository().findById(0L)).thenReturn(Optional.of(entity));

    Optional<B> optional = this.getService().findById(0L);

    assertTrue(optional.isPresent());
    B bean = optional.get();
    assertNotNull(bean);
    assertTrue(this.reflectiveCompare(entity, bean));
  }

  @Test
  public void findAll() {
    Pager page = new Pager(0L,25L);
    this.getService().findAll(page);
    verify(this.getRepository()).findAll(0L, 25L);
  }

  @Test
  public void  update(){
    B bean = this.buildBean();
    bean.setId(0L);
    bean.setName("update");

    when(getRepository().findById(0L)).thenReturn(Optional.of(this.buildEntity()));
    this.getService().update(bean);

    verify(this.getRepository()).save(any());
  }

  @Test
  public void  updateNotFound(){
    fail();
  }

  @Test
  public void  deleteById() {
    when(getRepository().findById(0L)).thenReturn(Optional.of(this.buildEntity()));
    this.getService().deleteById(0L);
    verify(this.getRepository()).delete(any());
  }

  @Test
  public void delete() {
    B bean = this.buildBean();
    bean.setId(0L);
    bean.setName("delete");
    when(getRepository().findById(0L)).thenReturn(Optional.of(this.buildEntity()));
    this.getService().deleteById(0L);
    verify(this.getRepository()).delete(any());
  }

  @Test
  public void deleteNotFound() {
    fail();
  }

  protected abstract E buildEntity();
  protected abstract B buildBean();


  protected boolean reflectiveCompare(E entity, B bean) {
    DynaBean dynaEntity = new WrapDynaBean(entity);
    DynaBean dynaBean = new WrapDynaBean(bean);
    boolean equals = false;
    for (DynaProperty entityProperty : dynaEntity.getDynaClass().getDynaProperties()) {
      if(dynaBean.getDynaClass().getDynaProperty(entityProperty.getName()) != null) {
        equals |= dynaBean.get(entityProperty.getName()).equals(dynaEntity.get(entityProperty.getName())) ;
      }
    }

    return equals;
  }

}
