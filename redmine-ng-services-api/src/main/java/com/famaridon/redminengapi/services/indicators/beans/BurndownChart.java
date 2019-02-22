package com.famaridon.redminengapi.services.indicators.beans;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class BurndownChart extends AbstractBean {

  private Iteration iteration;

  private List<ChartTimedValue> values = new ArrayList<>();

  public Iteration getIteration() {
    return iteration;
  }

  public void setIteration(Iteration iteration) {
    this.iteration = iteration;
  }

  public List<ChartTimedValue> getValues() {
    return values;
  }

  public void setValues(
      List<ChartTimedValue> values) {
    this.values = values;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof BurndownChart)) {
      return false;
    }

    BurndownChart that = (BurndownChart) o;

    return new EqualsBuilder()
        .appendSuper(super.equals(o))
        .append(iteration, that.iteration)
        .append(values, that.values)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .appendSuper(super.hashCode())
        .append(iteration)
        .append(values)
        .toHashCode();
  }
}
