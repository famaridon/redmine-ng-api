package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

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
}
