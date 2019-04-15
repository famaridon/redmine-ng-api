package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Journal extends AbstractRedmineBean {
	
	private User user;
	private String notes;
	@JsonProperty("created_on")
	private Date createdOn;
	private List<Detail> details = new ArrayList<>();
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public List<Detail> getDetails() {
		return details;
	}
	
	public void setDetails(List<Detail> details) {
		this.details = details;
	}
}
