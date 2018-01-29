package com.famaridon.redminengapi.services.redmine.rest.client.beans;

public abstract class CustomField<T> extends AbstractRedmineBean {
	
	private T value;
	private boolean multiple;
	
	public T getValue() {
		return this.value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public boolean isMultiple() {
		return this.multiple;
	}
	
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
}
