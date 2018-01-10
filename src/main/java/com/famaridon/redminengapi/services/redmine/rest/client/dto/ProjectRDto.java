package com.famaridon.redminengapi.services.redmine.rest.client.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectRDto extends AbstractRDto {
	
	public String identifier;
	public String description;
	public String homepage;
	public Long status;
	public Boolean is_public;
	public Date created_on;
	public Date updated_on;
	public ProjectRDto parent;
	public List<TrackerRDto> trackers = new ArrayList<>();
	public List<CategoryRDto> issue_categories = new ArrayList<>();
}
