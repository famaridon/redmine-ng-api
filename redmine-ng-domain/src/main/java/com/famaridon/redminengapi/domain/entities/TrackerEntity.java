package com.famaridon.redminengapi.domain.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TrackerEntity extends AbstractEntity {

	@ManyToMany
	private List<StatusEntity> statusList = new ArrayList<>();
	
	public List<StatusEntity> getStatusList() {
		return this.statusList;
	}
	
	public TrackerEntity setStatusList(List<StatusEntity> statusList) {
		this.statusList = statusList;
		return this;
	}
}
