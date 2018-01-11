package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;

public interface ProjectService {
	
	Project findById(String key, Long id);
	Page<Project> findAll(String key);
}
