package com.famaridon.redminengapi.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@AttributeOverrides({
    @AttributeOverride(name = "name", column = @Column(unique = false, nullable = true))
})
public class ChartTimedValueEntity extends AbstractEntity {

  @Column(nullable = false)
  protected LocalDateTime date;

  @Column(nullable = false)
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

    if (!(o instanceof ChartTimedValueEntity)) {
      return false;
    }

    ChartTimedValueEntity that = (ChartTimedValueEntity) o;

    return new EqualsBuilder()
        .appendSuper(super.equals(o))
        .append(id, that.id)
        .append(date, that.date)
        .append(value, that.value)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .appendSuper(super.hashCode())
        .append(id)
        .append(date)
        .append(value)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("date", date)
        .append("value", value)
        .toString();
  }
}
