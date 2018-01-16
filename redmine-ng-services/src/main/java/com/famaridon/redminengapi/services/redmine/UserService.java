package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;

public interface UserService {
	
	User findCurrent(String apiKey);
}
