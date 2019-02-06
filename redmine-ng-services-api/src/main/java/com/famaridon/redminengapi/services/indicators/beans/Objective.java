package com.famaridon.redminengapi.services.indicators.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Objective extends AbstractBean {

  private String description;
  private Iteration iteration;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Iteration getIteration() {
    return iteration;
  }

  public void setIteration(Iteration iteration) {
    this.iteration = iteration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Objective)) {
      return false;
    }

    Objective objective = (Objective) o;

    return new EqualsBuilder()
        .appendSuper(super.equals(o))
        .append(description, objective.description)
        .append(iteration, objective.iteration)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .appendSuper(super.hashCode())
        .append(description)
        .append(iteration)
        .toHashCode();
  }
}
