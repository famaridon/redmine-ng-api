package com.famaridon.redminengapi.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryDto extends AbstractDto {

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
}
