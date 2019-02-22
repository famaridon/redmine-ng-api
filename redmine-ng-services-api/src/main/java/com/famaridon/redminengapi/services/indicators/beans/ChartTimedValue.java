package com.famaridon.redminengapi.services.indicators.beans;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof ChartTimedValue)) {
      return false;
    }

    ChartTimedValue that = (ChartTimedValue) o;

    return new EqualsBuilder()
        .appendSuper(super.equals(o))
        .append(date, that.date)
        .append(value, that.value)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .appendSuper(super.hashCode())
        .append(date)
        .append(value)
        .toHashCode();
  }
}
