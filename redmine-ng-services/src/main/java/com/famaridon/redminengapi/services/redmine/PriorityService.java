package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Priority;

import java.io.IOException;
import java.util.Set;

public interface PriorityService {
	Set<Priority> findAll(String apiKey) throws IOException;
}
