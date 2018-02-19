package com.famaridon.redminengapi.services.realtime.impl;

import com.famaridon.redminengapi.services.realtime.UsersStatusService;
import com.famaridon.redminengapi.services.realtime.beans.RealtimeMessage;
import com.famaridon.redminengapi.services.realtime.beans.RealtimeMessageBuilder;
import com.famaridon.redminengapi.services.realtime.beans.UserStatus;
import com.famaridon.redminengapi.services.realtime.jms.NamedTopic;
import org.infinispan.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import java.util.HashSet;
import java.util.Set;

@MessageDriven(messageListenerInterface = MessageListener.class, activationConfig = {
	@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "AUTO_ACKNOWLEDGE"),
	@ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "java:/ConnectionFactory"),
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
	@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/redmine-ng-api/topic/realtime")
})
public class UsersStatusServiceImpl implements MessageListener, UsersStatusService
{
	
	private static final Logger LOG = LoggerFactory.getLogger(UsersStatusServiceImpl.class);
	
	@Inject
	private Cache<Long, Long> sessionsCountByUserCache;
	@Inject
	private Cache<UserStatus, Set<Long>> usersByUsersStatusCache;
	
	@Inject
	private Connection connection;
	private Session session;
	
	@Inject @NamedTopic("realtime")
	private Topic topic;
	private MessageProducer messageProducer;
	
	@PostConstruct
	private void init()
	{
		for (UserStatus userStatus : UserStatus.values()) {
			this.usersByUsersStatusCache.put(userStatus, new HashSet<>());
		}
		
		try {
			this.session = this.connection.createSession();
			this.messageProducer = this.session.createProducer(this.topic);
		}
		catch (JMSException e) {
			throw new IllegalStateException(e);
		}
	}
	
	@Override
	public void onMessage(Message message)
	{
		try {
			RealtimeMessage realtimeMessage = message.getBody(RealtimeMessage.class);
			if(CHANNEL.equalsIgnoreCase(realtimeMessage.getChannel())) {
				this.updateSessionsCountByUserCache(realtimeMessage);
				this.updateUsersByUsersStatusCache(realtimeMessage);
			}
			
		}
		catch (JMSException e) {
			LOG.error("JMS error", e);
		}
	}
	
	private void updateSessionsCountByUserCache(RealtimeMessage<UserStatus> userStatusMessage)
	{
		Long sessionCount = this.getUserSessionCount(userStatusMessage.getSender());
		if (userStatusMessage.getBody() == UserStatus.CONNECTED) {
			sessionCount++;
			this.sessionsCountByUserCache.put(userStatusMessage.getSender(), sessionCount);
		}
		else if (userStatusMessage.getBody() == UserStatus.DISCONNECTED) {
			sessionCount--;
			this.sessionsCountByUserCache.put(userStatusMessage.getSender(), sessionCount);
		}
	}
	
	private void updateUsersByUsersStatusCache(RealtimeMessage<UserStatus> userStatusMessage) throws JMSException
	{
		Long sessionCount = this.getUserSessionCount(userStatusMessage.getSender());
		switch (userStatusMessage.getBody()){
			case CONNECTED:
				if (sessionCount == 1) {
					this.usersByUsersStatusCache.get(UserStatus.CONNECTED).add(userStatusMessage.getSender());
					this.usersByUsersStatusCache.get(UserStatus.DISCONNECTED).remove(userStatusMessage.getSender());
					RealtimeMessage<UserStatus> joinMessage = new RealtimeMessageBuilder<UserStatus>()
						.setSender(userStatusMessage.getSender())
						.setChannel(UsersStatusService.CHANNEL)
						.setBody(UserStatus.JOIN)
						.createRealtimeMessage();
					this.messageProducer.send(this.session.createObjectMessage(joinMessage));
				}
				break;
			case DISCONNECTED:
				if (sessionCount <= 0) {
					this.usersByUsersStatusCache.get(UserStatus.DISCONNECTED).add(userStatusMessage.getSender());
					this.usersByUsersStatusCache.get(UserStatus.CONNECTED).remove(userStatusMessage.getSender());
					RealtimeMessage<UserStatus> joinMessage = new RealtimeMessageBuilder<UserStatus>()
						.setSender(userStatusMessage.getSender())
						.setChannel(UsersStatusService.CHANNEL)
						.setBody(UserStatus.LEAVE)
						.createRealtimeMessage();
					this.messageProducer.send(this.session.createObjectMessage(joinMessage));
				}
				break;
			case JOIN:
			case LEAVE:
				break;
			default:
				LOG.info("User session in invalid state {}", userStatusMessage.getSender());
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
	
}
