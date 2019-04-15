package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

	@JsonProperty("version")
	public void setVersion(T version) {
		this.element = version;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Holder)) {
			return false;
		}
		
		Holder<?> holder = (Holder<?>)o;
		
		return new EqualsBuilder().append(element, holder.element).isEquals();
	}
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder(17, 37).append(element).toHashCode();
	}
}
