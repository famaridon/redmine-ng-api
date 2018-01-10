package com.famaridon.redminengapi.rest.dto;

import com.famaridon.redminengapi.services.redmine.rest.client.dto.ProjectRDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectDto extends AbstractDto {
	
	public String identifier;
	public String description;
	public String homepage;
	public Long status;
	public Boolean is_public;
	public Date created_on;
	public Date updated_on;
	public ProjectRDto parent;
	public List<TrackerDto> trackers = new ArrayList<>();
	public List<CategoryDto> issue_categories = new ArrayList<>();
	
}
