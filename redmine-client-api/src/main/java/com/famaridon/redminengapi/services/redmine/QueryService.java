package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Query;

import java.io.IOException;

public interface QueryService {
	
	Page<Query> findAll(String apiKey, Pager pager) throws IOException;
}
