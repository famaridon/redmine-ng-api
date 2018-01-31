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
	
	/**
	 * set {@link TrackerEntity#statusList} property
	 *
	 * @param statusList set the statusList property
	 **/
	public void setStatusList(List<StatusEntity> statusList) {
		this.statusList = statusList;
	}
}
