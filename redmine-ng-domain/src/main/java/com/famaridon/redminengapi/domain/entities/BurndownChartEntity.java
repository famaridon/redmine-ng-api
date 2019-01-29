package com.famaridon.redminengapi.domain.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Lob;
@NamedQueries({
    @NamedQuery(name = "BurndownChartEntity.findByIteration", query = "select b from BurndownChartEntity b where b.iteration = :iteration"),
    @NamedQuery(name = "BurndownChartEntity.findCurrent", query = "select b from BurndownChartEntity b where b.iteration.start < :now and :now <= b.iteration.end")
})

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
