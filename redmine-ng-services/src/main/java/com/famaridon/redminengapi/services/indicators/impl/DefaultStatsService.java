package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.services.indicators.StatsService;
import com.famaridon.redminengapi.services.indicators.beans.IssueStats;
import com.famaridon.redminengapi.services.redmine.IssueAssociatedData;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Detail;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Journal;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.Optional;

@Stateless
public class DefaultStatsService implements StatsService {
	
	public static final String STATUS_ID = "status_id";
	@Inject
	private IssueService issueService;
	
	public DefaultStatsService() {
	}
	
	public DefaultStatsService( IssueService issueService) {
		this.issueService = issueService;
	}
	
	@Override
	public IssueStats computeIssueStats(String apikey, Issue issue) throws IOException {
		return this.computeIssueStats(apikey, issue.getId());
	}
	
	@Override
	public IssueStats computeIssueStats(String apikey, Long issueId) throws IOException {
		
		IssueStats issueStats = new IssueStats();
		
		Issue issue = this.issueService.findById(apikey, issueId, IssueAssociatedData.JOURNALS);
		issue.getJournals().sort(Comparator.comparing(Journal::getCreatedOn));

		Instant previousStatusChangeDate = issue.getCreatedOn().toInstant();
		for (Journal journal : issue.getJournals()) {
			Optional<Detail> statusChangeDetail = this.findStatusDetail(journal);
			if (statusChangeDetail.isPresent()) {
				Detail detail = statusChangeDetail.get();
				Long previousStatus = Long.parseLong(detail.getOldValue());
				Duration statusDuration = issueStats.getDurationByTracker().get(previousStatus);
				if (statusDuration == null) {
					statusDuration = Duration.ZERO;
				}
				statusDuration = statusDuration.plus(Duration.between(journal.getCreatedOn().toInstant(), previousStatusChangeDate));
				issueStats.getDurationByTracker().put(previousStatus, statusDuration);
				previousStatusChangeDate = journal.getCreatedOn().toInstant();
			}
		}
		
		return issueStats;
	}
	
	private Optional<Detail> findStatusDetail(Journal journal) {
		return journal.getDetails().stream().filter(detail -> STATUS_ID.equals(detail.getName())).findFirst();
	}
}

