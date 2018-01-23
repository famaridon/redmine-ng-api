package com.famaridon.redminengapi.rest.dto;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class GroupDto extends AbstractDto {

	@JsonProperty("user_ids")
	private List<Long> userIds = new ArrayList<>();
	
	public List<Long> getUserIds() {
		return this.userIds;
	}
	
	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
}
