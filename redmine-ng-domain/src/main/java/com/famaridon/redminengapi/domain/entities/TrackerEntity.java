package com.famaridon.redminengapi.domain.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TrackerEntity extends AbstractExternalEntity {

	@ManyToMany
	private Set<StatusEntity> statusSet = new HashSet<>();
	
	public Set<StatusEntity> getStatusSet() {
		return this.statusSet;
	}
	
	/**
	 * set {@link TrackerEntity#statusSet} property
	 *
	 * @param statusSet set the statusSet property
	 **/
	public void setStatusSet(Set<StatusEntity> statusSet) {
		this.statusSet = statusSet;
	}
}
