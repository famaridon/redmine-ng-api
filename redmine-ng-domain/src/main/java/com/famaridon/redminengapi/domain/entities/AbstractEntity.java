package com.famaridon.redminengapi.domain.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Id
	private Long id;
	private String name;
	
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
}
