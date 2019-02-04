package com.famaridon.redminengapi.services.redmine.mock;

import com.famaridon.redminengapi.services.redmine.RedmineClientConfiguration;

import javax.enterprise.inject.Default;
import java.util.List;

@Default
public class MockRedmineClientConfiguration implements RedmineClientConfiguration
{
	
	@Override
	public String getServerUrl()
	{
		return System.getenv("TEST_REDMINE_SERVER");
	}
	
	@Override
	public List<String> getUserRoles(String login)
	{
		return null;
	}
}
