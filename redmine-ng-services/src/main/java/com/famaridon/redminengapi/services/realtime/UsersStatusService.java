package com.famaridon.redminengapi.services.realtime;

import com.famaridon.redminengapi.services.realtime.beans.UserStatus;

import java.util.Set;

public interface UsersStatusService
{
	public Set<Long> getUserByUsersStatus(UserStatus userStatus);
}
