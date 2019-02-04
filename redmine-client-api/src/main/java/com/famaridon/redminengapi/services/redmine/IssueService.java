package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;

import java.io.IOException;
import java.util.List;

public interface IssueService {
	
	Page<Issue> findAll(String apiKey, Pager pager) throws IOException;
	
	Issue findById(String apiKey, Long id) throws IOException;
	
	Page<Issue> findByQueryAndProject(String apiKey, Long query, Long project, Pager pager) throws IOException;

	Page<Issue> findAllByFilters(String apiKey, List<Filter> filters, Pager pager) throws IOException;

	Long findCount(String apiKey, Long query, Long project) throws IOException;
}
