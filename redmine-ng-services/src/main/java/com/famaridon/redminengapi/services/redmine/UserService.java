package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
	
	User findCurrent(String apiKey) throws IOException;
	User findById(String apiKey, long id) throws IOException;
	List<String> findRoles(String login );
}
