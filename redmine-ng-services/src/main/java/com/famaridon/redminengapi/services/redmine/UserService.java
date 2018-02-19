package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.realtime.beans.UserStatus;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;

import java.io.IOException;
import java.util.Set;

public interface UserService {
	
	User findCurrent(String apiKey) throws IOException;
	User findById(String apiKey, long id) throws IOException;
	
	Set<Long> getUserByUsersStatus(UserStatus userStatus);
}
