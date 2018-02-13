package com.famaridon.redminengapi.services.realtime.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class UserStatusMessage implements Serializable
{
	private final Long userId;
	private final UserStatus userStatus;
	
	public UserStatusMessage(Long userId, UserStatus userStatus)
	{
		this.userId = userId;
		this.userStatus = userStatus;
	}
	
	public Long getUserId()
	{
		return this.userId;
	}
	
	public UserStatus getUserStatus()
	{
		return this.userStatus;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) { return true; }
		
		if (!(o instanceof UserStatusMessage)) { return false; }
		
		UserStatusMessage that = (UserStatusMessage)o;
		
		return new EqualsBuilder().append(this.userId, that.userId).isEquals();
	}
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder(17, 37).append(this.userId).toHashCode();
	}
	
	@Override
	public String toString()
	{
		return new ToStringBuilder(this).append("userId", userId).append("userStatus", userStatus).toString();
	}
}
