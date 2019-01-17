package com.famaridon.redminengapi.domain.entities;

import javax.persistence.ManyToOne;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class ObjectiveEntity extends AbstractEntity {

  @Length(min = 10, max = 512)
  private String summary;

  @Lob
  private String description;

  @ManyToOne
  private IterationEntity iteration;

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public IterationEntity getIteration() {
    return iteration;
  }

  public void setIteration(IterationEntity iteration) {
    this.iteration = iteration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ObjectiveEntity that = (ObjectiveEntity) o;

    return new EqualsBuilder()
        .appendSuper(super.equals(o))
        .append(summary, that.summary)
        .append(description, that.description)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(summary)
        .append(description)
        .toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("summary", summary)
        .append("description", description)
        .append("id", id)
        .append("name", name)
        .toString();
  }
}
