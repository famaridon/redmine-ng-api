package com.famaridon.redminengapi.services.redmine.rest.client.beans;

public class CustomField extends AbstractRedmineBean {
	
	private String value;
	
	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
