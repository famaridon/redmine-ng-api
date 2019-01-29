package com.famaridon.redminengapi.services.indicators.beans;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ChartTimedValue extends AbstractBean {

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
