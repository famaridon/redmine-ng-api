package com.famaridon.redminengapi.rest.dto;

import com.famaridon.redminengapi.rest.refs.IterationRef;
import java.util.ArrayList;
import java.util.List;

public class BurndownChartDto extends AbstractDto {
  private IterationRef iteration;

  private List<ChartTimedValueDto> values = new ArrayList<>();

  public IterationRef getIteration() {
    return iteration;
  }

  public void setIteration(IterationRef iteration) {
    this.iteration = iteration;
  }

  public List<ChartTimedValueDto> getValues() {
    return values;
  }

  public void setValues(List<ChartTimedValueDto> values) {
    this.values = values;
  }
}
