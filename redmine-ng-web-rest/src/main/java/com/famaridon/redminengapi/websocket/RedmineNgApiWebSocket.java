package com.famaridon.redminengapi.websocket;

import com.famaridon.redminengapi.rest.api.SecurityHeaders;
import com.famaridon.redminengapi.services.realtime.beans.UserStatus;
import com.famaridon.redminengapi.services.realtime.beans.UserStatusMessage;
import com.famaridon.redminengapi.services.realtime.jms.NamedTopic;
import com.famaridon.redminengapi.services.redmine.UserService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/ws",configurator = RedmineAPIKeyConfigurator.class)
public class RedmineNgApiWebSocket
{

	private static final Logger LOG = LoggerFactory.getLogger(RedmineNgApiWebSocket.class);
	
	@Inject @NamedTopic("users")
	private Topic usersTopic;
	
	@Inject @NamedTopic("issues")
	private Topic issuesTopic;
	
	@Inject
	private Connection connection;
	
	@Inject
	private UserService userService;
	
	private User user;
	
	private Session wsSession;
	private javax.jms.Session jmsSession;
	private MessageProducer usersTopicProducer;
	private MessageConsumer usersTopicConsumer;
	
	private MessageProducer issuesTopicProducer;
	private MessageConsumer issuesTopicConsumer;
	
	@OnOpen
	public void onOpen (Session session, EndpointConfig config) throws JMSException, IOException
	{
		this.wsSession = session;
		this.user = this.userService.findCurrent(session.getRequestParameterMap().get(SecurityHeaders.X_REDMINE_API_KEY).get(0));
		LOG.debug("User {} open WebSocket session {}",this.user.getLogin(), this.wsSession.getId());
		
		// sync websocket and jms session ids
		this.connection.setClientID(this.wsSession.getId());
		
		// create and configure jms session
		this.jmsSession = this.connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
		
		this.usersTopicProducer = this.jmsSession.createProducer(this.usersTopic);
		this.usersTopicConsumer = this.jmsSession.createConsumer(this.usersTopic);
		this.usersTopicConsumer.setMessageListener(message -> onUsersMessage(message));
		
		this.issuesTopicProducer = this.jmsSession.createProducer(this.issuesTopic);
		this.issuesTopicConsumer = this.jmsSession.createConsumer(this.issuesTopic);
		this.issuesTopicConsumer.setMessageListener(message -> onIssuesMessage(message));
		
		// start listening
		this.connection.start();
		
		this.usersTopicProducer.send(this.jmsSession.createObjectMessage(new UserStatusMessage(this.user.getId(), UserStatus.CONNECTED)));
	}
	
	@OnMessage
	public void onClientMessage(String json) throws JMSException
	{
		LOG.trace("User {} open WebSocket session {}",this.user.getLogin(), this.wsSession.getId());
		this.issuesTopicProducer.send(this.jmsSession.createObjectMessage(json));
	}
	
	public void onUsersMessage(Message message)
	{
		try {
			if(this.wsSession.isOpen()) {
				this.wsSession.getBasicRemote().sendText(message.getBody(UserStatusMessage.class).toString());
			}
		}
		catch (IOException | JMSException e) {
			LOG.error("JMS error : ",e);
		}
	}
	
	public void onIssuesMessage(Message message)
	{
		try {
			if(this.wsSession.isOpen()) {
				this.wsSession.getBasicRemote().sendText((String)((ObjectMessage)message).getObject());
			}
		}
		catch (IOException | JMSException e) {
			LOG.error("JMS error : ",e);
		}
	}
	
	@OnClose
	public void onClose (CloseReason reason) throws JMSException
	{
		LOG.debug("User {} close WebSocket session {}",this.user.getLogin(), this.wsSession.getId());
		this.connection.stop();
		this.usersTopicProducer.send(this.jmsSession.createObjectMessage(new UserStatusMessage(this.user.getId(), UserStatus.DISCONNECTED)));
		this.connection.close();
	}
	
	@OnError
	public void onError(Session session, Throwable t) throws JMSException
	{
		LOG.debug("User {} error WebSocket session {}",this.user.getLogin(), this.wsSession.getId());
	}
	
	
}
