package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.rest.dto.Paginable;
import com.famaridon.redminengapi.services.redmine.rest.client.dto.ProjectRDto;

public interface ProjectService {

	ProjectRDto findById(String key, Long id);
	Paginable<ProjectRDto> findAll(String key);
}
