package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.indicators.StatsService;
import com.famaridon.redminengapi.services.indicators.beans.IssueStats;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DefaultStatsService implements StatsService {
	
	@Inject
	private ConfigurationService configurationService;
	@Inject
	private IssueService issueService;
	
	@Override
	public IssueStats computeIssueStats(String apikey, Issue issue) {
		return this.computeIssueStats(apikey, issue.getId());
	}
	
	@Override
	public IssueStats computeIssueStats(String apikey, Long issueId) {
		return null;
	}
}

