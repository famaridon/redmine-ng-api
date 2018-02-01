package com.famaridon.redminengapi.domain.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractExternalEntity extends AbstractEntity {
	
	@Column(nullable = false, unique = true)
	private Long externalId;
	
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
}
