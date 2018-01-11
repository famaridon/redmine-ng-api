package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class AbstractRedmineBean {
	
	private long id;
	private String name;
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof AbstractRedmineBean)) {
			return false;
		}
		
		AbstractRedmineBean that = (AbstractRedmineBean)o;
		
		return new EqualsBuilder().append(this.id, that.id).append(this.name, that.name).isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(this.id).append(this.name).toHashCode();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("name", this.name).toString();
	}
}
