package com.famaridon.redminengapi.services.redmine;

import java.util.List;

public interface RedmineClientConfiguration
{
	
	String getServerUrl();
	
	List<String> getUserRoles(String login);
}
