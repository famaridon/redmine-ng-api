package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class Group extends AbstractRedmineBean {

	@JsonProperty("user_ids")
	private List<Long> userIds = new ArrayList<>();
	
	public List<Long> getUserIds() {
		return this.userIds;
	}
	
	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Group)) {
			return false;
		}
		
		Group group = (Group)o;
		
		return new EqualsBuilder().appendSuper(super.equals(o)).append(userIds, group.userIds).isEquals();
	}
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(userIds).toHashCode();
	}
}
