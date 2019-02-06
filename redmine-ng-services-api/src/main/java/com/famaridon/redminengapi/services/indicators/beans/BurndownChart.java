package com.famaridon.redminengapi.services.indicators.beans;

import java.util.ArrayList;
import java.util.List;

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
}
