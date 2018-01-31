package com.famaridon.redminengapi.domain.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
	@NamedQuery(name="WorkflowEntity.findByTrackerAndStatus", query = "select w from WorkflowEntity w where w.tracker = :tracker and w.status = :status"),
	@NamedQuery(name="WorkflowEntity.findByTrackerIdAndStatusId", query = "select w from WorkflowEntity w where w.tracker.id = :tracker and ((:status is null and w.status is null) or w.status.id = :status )")
})
@Entity
public class WorkflowEntity extends AbstractEntity {

	@ManyToMany
	private List<StatusEntity> availableStatusList = new ArrayList<>();
	
	@ManyToOne(optional = false)
	private TrackerEntity tracker;
	
	@ManyToOne
	private StatusEntity status;
	
	public List<StatusEntity> getAvailableStatusList() {
		return this.availableStatusList;
	}
	
	/**
	 * set {@link WorkflowEntity#availableStatusList} property
	 *
	 * @param availableStatusList set the availableStatusList property
	 **/
	public void setAvailableStatusList(List<StatusEntity> availableStatusList) {
		this.availableStatusList = availableStatusList;
	}
	
	public TrackerEntity getTracker() {
		return this.tracker;
	}
	
	/**
	 * set {@link WorkflowEntity#tracker} property
	 *
	 * @param tracker set the tracker property
	 **/
	public void setTracker(TrackerEntity tracker) {
		this.tracker = tracker;
	}
	
	public StatusEntity getStatus() {
		return this.status;
	}
	
	/**
	 * set {@link WorkflowEntity#status} property
	 *
	 * @param status set the status property
	 **/
	public void setStatus(StatusEntity status) {
		this.status = status;
	}
}
