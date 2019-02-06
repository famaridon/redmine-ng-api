package com.famaridon.redminengapi.services.indicators.beans;

import java.time.LocalDate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Iteration extends AbstractBean {

  private LocalDate start;
  private LocalDate end;
  private Long number;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Iteration)) {
      return false;
    }

    Iteration iteration = (Iteration) o;

    return new EqualsBuilder()
        .appendSuper(super.equals(o))
        .append(start, iteration.start)
        .append(end, iteration.end)
        .append(number, iteration.number)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .appendSuper(super.hashCode())
        .append(start)
        .append(end)
        .append(number)
        .toHashCode();
  }
}
