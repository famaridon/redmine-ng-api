package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.indicators.beans.IssueStats;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;

public interface IssuesStatsService {

	IssueStats computeIssueStats(String apikey, Long issueId);
	IssueStats computeIssueStats(String apikey, Issue issue);
	
}
