package com.famaridon.redminengapi.domain.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.time.LocalDate;
import java.util.Date;

@NamedQueries({
	@NamedQuery(name = "IterationEntity.findCurrentIteration", query = "select i from IterationEntity i where i.start < :now and :now < i.end"),
	@NamedQuery(name = "IterationEntity.findByName", query = "select i from IterationEntity i where i.name = :name") })

@Entity
public class IterationEntity extends AbstractEntity {
	
	protected LocalDate start;
	protected LocalDate end;
	
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
}
