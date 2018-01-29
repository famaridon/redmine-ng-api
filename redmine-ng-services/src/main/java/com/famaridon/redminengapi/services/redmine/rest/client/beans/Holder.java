package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Holder<T> {
	
	private T element ;
	
	public void setElement(T element) {
		this.element = element;
	}
	
	public T getElement() {
		return this.element;
	}
	
	@JsonProperty("project")
	public void setProject(T project) {
		this.element = project;
	}
	
	@JsonProperty("user")
	public void setUser(T user) {
		this.element = user;
	}
	
	@JsonProperty("issue")
	public void setIssue(T issue) {
		this.element = issue;
	}
	
}
