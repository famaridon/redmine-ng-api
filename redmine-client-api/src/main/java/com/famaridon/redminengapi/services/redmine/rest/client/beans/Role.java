package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Role extends AbstractRedmineBean {
	private Boolean inherited;
	
	public Boolean getInherited() {
		return this.inherited;
	}
	
	public void setInherited(Boolean inherited) {
		this.inherited = inherited;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Role)) {
			return false;
		}
		
		Role role = (Role)o;
		
		return new EqualsBuilder().appendSuper(super.equals(o)).append(inherited, role.inherited).isEquals();
	}
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(inherited).toHashCode();
	}
}
