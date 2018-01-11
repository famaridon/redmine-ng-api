package com.famaridon.redminengapi.rest.mapper;

import com.famaridon.redminengapi.rest.dto.CategoryDto;
import com.famaridon.redminengapi.rest.dto.ProjectDto;
import com.famaridon.redminengapi.rest.dto.TrackerDto;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Category;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Tracker;
import org.mapstruct.Mapper;

@Mapper
public interface DtoMapper {

	ProjectDto projectToProjectDto(Project project);
	TrackerDto trackerToTrackerDto(Tracker tracker);
	CategoryDto categoryToCategoryDto(Category category);

}
