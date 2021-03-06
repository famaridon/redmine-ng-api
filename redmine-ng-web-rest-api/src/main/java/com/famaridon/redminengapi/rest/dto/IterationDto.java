package com.famaridon.redminengapi.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class IterationDto extends AbstractDto {

  private LocalDate start;
  private LocalDate end;
  private Long number;
  private BigDecimal plannedDevelopmentCost;

  public LocalDate getStart() {
    return start;
  }

  public void setStart(LocalDate start) {
    this.start = start;
  }

  public LocalDate getEnd() {
    return end;
  }

  public void setEnd(LocalDate end) {
    this.end = end;
  }

  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }

  public BigDecimal getPlannedDevelopmentCost() {
    return plannedDevelopmentCost;
  }

  public void setPlannedDevelopmentCost(BigDecimal plannedDevelopmentCost) {
    this.plannedDevelopmentCost = plannedDevelopmentCost;
  }
}
