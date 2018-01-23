package com.famaridon.redminengapi.services.redmine.rest.client.beans;

public class Role extends AbstractRedmineBean {
	private Boolean inherited;
	
	public Boolean getInherited() {
		return this.inherited;
	}
	
	public void setInherited(Boolean inherited) {
		this.inherited = inherited;
	}
}
