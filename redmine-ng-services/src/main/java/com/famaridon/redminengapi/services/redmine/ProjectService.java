package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;

import java.io.IOException;

public interface ProjectService {
	
	Project findById(String apiKey, Long id) throws IOException;
	Page<Project> findAll(String apiKey) throws IOException;
}
