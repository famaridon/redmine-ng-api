package com.famaridon.redminengapi.rest.mapper;

import com.famaridon.redminengapi.rest.dto.CategoryDto;
import com.famaridon.redminengapi.rest.dto.ProjectDto;
import com.famaridon.redminengapi.rest.dto.TrackerDto;
import com.famaridon.redminengapi.services.redmine.rest.client.dto.ProjectRDto;
import com.famaridon.redminengapi.services.redmine.rest.client.dto.TrackerRDto;
import org.mapstruct.Mapper;

@Mapper
public interface DtoMapper {

	ProjectDto projectToProjectDto(ProjectRDto project);
	TrackerDto trackerToTrackerDto(TrackerRDto tracker);
	CategoryDto categoryToCategoryDto(CategoryDto category);

}
