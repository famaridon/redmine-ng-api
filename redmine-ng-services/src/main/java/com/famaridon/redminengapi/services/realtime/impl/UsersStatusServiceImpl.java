package com.famaridon.redminengapi.services.realtime.impl;

import com.famaridon.redminengapi.services.realtime.UsersStatusService;
import com.famaridon.redminengapi.services.realtime.beans.UserStatus;
import com.famaridon.redminengapi.services.realtime.beans.UserStatusMessage;
import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.HashSet;
import java.util.Set;

@MessageDriven(messageListenerInterface = MessageListener.class, activationConfig = {
	@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "AUTO_ACKNOWLEDGE"),
	@ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "java:/ConnectionFactory"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
	@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/redmine-ng-api/topic/users")
})
public class UsersStatusServiceImpl implements MessageListener, UsersStatusService
{
	
	private static final Logger LOG = LoggerFactory.getLogger(UsersStatusServiceImpl.class);
	
	@Inject
	private Cache<Long, Long> sessionsCountByUserCache;
	@Inject
	private Cache<UserStatus, Set<Long>> usersByUsersStatusCache;
	
	@PostConstruct
	private void init()
	{
		for (UserStatus userStatus : UserStatus.values()) {
			this.usersByUsersStatusCache.put(userStatus, new HashSet<>());
		}
	}
	
	@Override
	public void onMessage(Message message)
	{
		try {
			UserStatusMessage userStatusMessage = message.getBody(UserStatusMessage.class);
			this.updateSessionsCountByUserCache(userStatusMessage);
			this.updateUsersByUsersStatusCache(userStatusMessage);
		}
		catch (JMSException e) {
			LOG.error("JMS error", e);
		}
	}
	
	private void updateSessionsCountByUserCache(UserStatusMessage userStatusMessage)
	{
		Long sessionCount = this.getUserSessionCount(userStatusMessage.getUserId());
		if (userStatusMessage.getUserStatus() == UserStatus.CONNECTED) {
			sessionCount++;
		}
		else if (userStatusMessage.getUserStatus() == UserStatus.DISCONNECTED) {
			sessionCount--;
		}
		else if (userStatusMessage.getUserStatus() == UserStatus.UNKNOW) {
			LOG.info("User session in invalid state {}", userStatusMessage.getUserId());
		}
		this.sessionsCountByUserCache.put(userStatusMessage.getUserId(), sessionCount);
	}
	
	private void updateUsersByUsersStatusCache(UserStatusMessage userStatusMessage)
	{
		switch (userStatusMessage.getUserStatus()){
			case CONNECTED:
				this.usersByUsersStatusCache.get(UserStatus.CONNECTED).add(userStatusMessage.getUserId());
				this.usersByUsersStatusCache.get(UserStatus.DISCONNECTED).add(userStatusMessage.getUserId());
				break;
			case DISCONNECTED:
				Long sessionCount = this.getUserSessionCount(userStatusMessage.getUserId());
				if (sessionCount <= 0) {
					this.usersByUsersStatusCache.get(UserStatus.DISCONNECTED).add(userStatusMessage.getUserId());
					this.usersByUsersStatusCache.get(UserStatus.CONNECTED).add(userStatusMessage.getUserId());
				}
				break;
			default:
				LOG.info("User session in invalid state {}", userStatusMessage.getUserId());
		}
	}
	
	private Long getUserSessionCount(Long userId)
	{
		Long sessionCount = this.sessionsCountByUserCache.get(userId);
		if (sessionCount == null) {
			sessionCount = 0L;
		}
		return sessionCount;
	}
	
	@Override
	public Set<Long> getUserByUsersStatus(UserStatus userStatus)
	{
		return this.usersByUsersStatusCache.get(userStatus);
	}
}
