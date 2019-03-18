package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.services.indicators.StatsService;
import com.famaridon.redminengapi.services.indicators.beans.IssueStats;
import com.famaridon.redminengapi.services.redmine.IssueAssociatedData;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Detail;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Journal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class DefaultStatsServiceTest {
	
	private static final String MOCK_API_KEY = "mock";
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
	
	protected StatsService statsService;
	protected IssueService mockedIssueService;
	
	@Before
	public void initialize() {
		this.mockedIssueService = mock(IssueService.class);
		this.statsService = new DefaultStatsService(this.mockedIssueService );
	}
	
	@Test
	public void computeIssueStatsWithoutJournal() throws IOException {
		when(this.mockedIssueService.findById(MOCK_API_KEY, 1L, IssueAssociatedData.JOURNALS)).thenReturn(this.buildIssueWithoutJournal());
		IssueStats issueStats = this.statsService.computeIssueStats(MOCK_API_KEY, 1L);
	}
	
	@Test
	public void computeIssueStatsWithJournal() throws IOException {
		when(this.mockedIssueService.findById(MOCK_API_KEY, 1L, IssueAssociatedData.JOURNALS)).thenReturn(this.buildIssueWithJournal());
		IssueStats issueStats = this.statsService.computeIssueStats(MOCK_API_KEY, 1L);
		System.out.println(issueStats);
	}
	
	private Issue buildIssueWithoutJournal() {
		Issue issue = new Issue();
		issue.setCreatedOn(getDate("2019/01/01"));
		issue.setId(1L);
		return issue;
	}
	
	private Date getDate(String date){
		try {
			return DATE_FORMAT.parse(date);
		} catch (ParseException e) {
			throw new IllegalStateException("Test date format is broken!", e);
		}
	}
	
	private Issue buildIssueWithJournal() {
		Issue issue = this.buildIssueWithoutJournal();
		issue.getJournals().add(this.buildStatusChangeJournal("2019/01/02","1","2"));
		issue.getJournals().add(this.buildStatusChangeJournal("2019/01/03","2","3"));
		issue.getJournals().add(this.buildStatusChangeJournal("2019/01/04","3","1"));
		issue.getJournals().add(this.buildStatusChangeJournal("2019/01/05","1","2"));
		return issue;
	}
	
	private Journal buildStatusChangeJournal(String date, String oldstatus, String newStatus) {
		Journal journal = new Journal();
		journal.setCreatedOn(this.getDate(date));
		Detail detail = new Detail();
		journal.getDetails().add(detail);
		detail.setName("status_id");
		detail.setOldValue(oldstatus);
		detail.setNewValue(newStatus);
		
		return journal;
	}
}