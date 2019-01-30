package com.famaridon.redminengapi.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ChartTimedValueDto extends AbstractDto {

  protected LocalDateTime date;
  protected BigDecimal value;

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }
}
