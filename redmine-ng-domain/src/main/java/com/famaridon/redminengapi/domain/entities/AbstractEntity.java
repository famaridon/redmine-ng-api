package com.famaridon.redminengapi.domain.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public abstract class AbstractEntity {
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Id
	protected Long id;
	
	@Column(unique = true)
	protected String name;
	
	public Long getId() {
		return this.id;
	}
	
	public AbstractEntity setId(Long id) {
		this.id = id;
		return this;
	}
	
	public String getName() {
		return this.name;
	}
	
	public AbstractEntity setName(String name) {
		this.name = name;
		return this;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("name", name).toString();
	}
}
