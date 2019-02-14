package com.famaridon.redminengapi.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@NamedQuery(name = "IterationEntity.findCurrentIteration", query = "select i from IterationEntity i where i.start < :now and :now <= i.end")
@NamedQuery(name = "IterationEntity.findByNumber", query = "select i from IterationEntity i where i.number = :number")
@Entity
public class IterationEntity extends AbstractEntity {

  @Column(nullable = false)
  protected LocalDate start;
  @Column(nullable = false)
  protected LocalDate end;
  @Column(unique = true, nullable = false)
  protected Long number;
  @Column(nullable = false)
  protected BigDecimal plannedDevelopmentCost = BigDecimal.ZERO;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof IterationEntity)) {
      return false;
    }

    IterationEntity that = (IterationEntity) o;
    boolean plannedPoints = plannedDevelopmentCost.compareTo(that.plannedDevelopmentCost) == 0;
    return new EqualsBuilder()
        .appendSuper(super.equals(o))
        .append(start, that.start)
        .append(end, that.end)
        .append(number, that.number)
        .isEquals() && plannedPoints;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .appendSuper(super.hashCode())
        .append(start)
        .append(end)
        .append(number)
        .append(plannedDevelopmentCost)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("start", start)
        .append("end", end)
        .append("number", number)
        .append("id", id)
        .append("name", name)
        .append("plannedDevelopmentCost", plannedDevelopmentCost)
        .toString();
  }
}
