package com.famaridon.redminengapi.domain.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class ObjectiveEntity extends AbstractEntity {

    @Length(min = 10, max = 512)
    private String summary;

    @Lob
    private String description;

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
}
