package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.rest.client.dto.ProjectRDto;
import com.taskadapter.redmineapi.bean.Project;

import java.util.List;

public interface ProjectService {
	
	ProjectRDto findById(String key, Long id);
	List<Project> findAll(String key);
}
