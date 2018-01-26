package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

@Stateless
public class DefaultIssueService extends AbstractRedmineService<Issue> implements IssueService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultIssueService.class);
	
	public DefaultIssueService() {
	}
	
	public DefaultIssueService(ConfigurationService configurationService) {
	this.configurationService = configurationService;
	}
	
}
