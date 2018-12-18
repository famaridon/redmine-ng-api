package com.famaridon.redminengapi.domain.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.Date;

@Entity
public class IterationEntity extends AbstractEntity {

    protected Date start;
    protected Date end;
    protected Integer number;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
    
    public Integer getNumber()
    {
        return number;
    }
    
    public void setNumber(Integer number)
    {
        this.number = number;
    }
}
