package com.famaridon.redminengapi.domain.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractExternalEntity extends AbstractEntity {
	
	@Column(nullable = false, unique = true)
	protected Long externalId;
	
	public Long getExternalId() {
		return this.externalId;
	}
	
	/**
	 * set {@link AbstractExternalEntity#externalId} property
	 *
	 * @param externalId set the externalId property
	 **/
	public void setExternalId(Long externalId) {
		this.externalId = externalId;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("externalId", externalId).append("id", id).append("name", name).toString();
	}
}
