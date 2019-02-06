package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Query extends AbstractRedmineBean {
	
	@JsonProperty("is_public")
	private boolean isPublic;
	@JsonProperty("project_id")
	private long projectId;
	
	public boolean isPublic() {
		return this.isPublic;
	}
	
	public void setPublic(boolean aPublic) {
		this.isPublic = aPublic;
	}
	
	public long getProjectId() {
		return this.projectId;
	}
	
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Query)) {
			return false;
		}
		
		Query query = (Query)o;
		
		return new EqualsBuilder().appendSuper(super.equals(o)).append(isPublic, query.isPublic).append(projectId, query.projectId).isEquals();
	}
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(isPublic).append(projectId).toHashCode();
	}
}
