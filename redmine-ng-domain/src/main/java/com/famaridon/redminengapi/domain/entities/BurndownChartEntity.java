package com.famaridon.redminengapi.domain.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class BurndownChartEntity extends AbstractEntity {

  @ManyToOne(optional = false)
  private IterationEntity iteration;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ChartTimedValueEntity> values = new ArrayList<>();

  public IterationEntity getIteration() {
    return iteration;
  }

  public void setIteration(IterationEntity iteration) {
    this.iteration = iteration;
  }

  public List<ChartTimedValueEntity> getValues() {
    return values;
  }

  public void setValues(
      List<ChartTimedValueEntity> values) {
    this.values = values;
  }
}
