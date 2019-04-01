package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.indicators.beans.IssueStats;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;

import java.io.IOException;

public interface StatsService {

	IssueStats computeIssueStats(String apikey, Long issueId) throws IOException;
	IssueStats computeIssueStats(String apikey, Issue issue) throws IOException;
	
}
