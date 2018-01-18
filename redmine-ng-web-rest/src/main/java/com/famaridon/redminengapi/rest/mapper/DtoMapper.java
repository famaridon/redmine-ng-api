package com.famaridon.redminengapi.rest.mapper;

import com.famaridon.redminengapi.rest.dto.CategoryDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.ProjectDto;
import com.famaridon.redminengapi.rest.dto.TrackerDto;
import com.famaridon.redminengapi.rest.dto.UserDto;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Category;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Tracker;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface DtoMapper {
	@Mappings(
		@Mapping(target = "elements", ignore = true)
	)
	PageDto pageToPageDto(Page page);
	
	UserDto userToUserDto(User project);
	ProjectDto projectToProjectDto(Project project);
	List<ProjectDto> projectsToProjectDtos(List<Project> project);
	
	TrackerDto trackerToTrackerDto(Tracker tracker);
	CategoryDto categoryToCategoryDto(Category category);

}
