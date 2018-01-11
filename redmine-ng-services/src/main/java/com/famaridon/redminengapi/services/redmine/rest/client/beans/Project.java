package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"is_public"})
public class Project extends AbstractRedmineBean {
	
	private String identifier;
	private String description;
	private String homepage;
	private long status;
	@JsonProperty("created_on")
	private Date createdOn;
	@JsonProperty("updated_on")
	private Date updatedOn;
	private Project parent;
	private List<Tracker> trackers = new ArrayList<>();
	@JsonProperty("issue_categories")
	private List<Category> issueCategories = new ArrayList<>();
	
	public String getIdentifier() {
		return this.identifier;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getHomepage() {
		return this.homepage;
	}
	
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	public long getStatus() {
		return this.status;
	}
	
	public void setStatus(long status) {
		this.status = status;
	}
	
	public Date getCreatedOn() {
		return this.createdOn;
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public Date getUpdatedOn() {
		return this.updatedOn;
	}
	
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	public Project getParent() {
		return this.parent;
	}
	
	public void setParent(Project parent) {
		this.parent = parent;
	}
	
	public List<Tracker> getTrackers() {
		return this.trackers;
	}
	
	public void setTrackers(List<Tracker> trackers) {
		this.trackers = trackers;
	}
	
	public List<Category> getIssueCategories() {
		return this.issueCategories;
	}
	
	public void setIssueCategories(List<Category> issueCategories) {
		this.issueCategories = issueCategories;
	}
}
