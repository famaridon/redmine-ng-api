package com.famaridon.redminengapi.websocket;

import com.famaridon.redminengapi.rest.api.SecurityHeaders;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.realtime.UsersStatusService;
import com.famaridon.redminengapi.services.realtime.beans.RealtimeMessage;
import com.famaridon.redminengapi.services.realtime.beans.RealtimeMessageBuilder;
import com.famaridon.redminengapi.services.realtime.beans.UserStatus;
import com.famaridon.redminengapi.services.realtime.jms.NamedTopic;
import com.famaridon.redminengapi.services.redmine.UserService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
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
	
	
	@Inject
	private ObjectMapper objectMapper;
	
	@Inject
	private DtoMapper mapper;
	
	@Inject @NamedTopic("realtime")
	private Topic realtimeTopic;
	
	@Inject
	private Connection connection;
	
	@Inject
	private UserService userService;
	
	private User user;
	
	private Session wsSession;
	private javax.jms.Session jmsSession;
	
	private MessageProducer realtimeTopicProducer;
	private MessageConsumer realtimeTopicConsumer;
	
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
		
		this.realtimeTopicProducer = this.jmsSession.createProducer(this.realtimeTopic);
		this.realtimeTopicConsumer = this.jmsSession.createConsumer(this.realtimeTopic);
		this.realtimeTopicConsumer.setMessageListener(message -> onRealtimeMessage(message));
		
		// start listening
		this.connection.start();
		
		RealtimeMessage<UserStatus> connectMessage = new RealtimeMessageBuilder<UserStatus>()
			.setSender(this.user.getId())
			.setChannel(UsersStatusService.CHANNEL)
			.setBody(UserStatus.CONNECTED)
			.createRealtimeMessage();
		
		this.realtimeTopicProducer.send(this.jmsSession.createObjectMessage(connectMessage));
	}
	
	@OnMessage
	public void onClientMessage(String json) throws JMSException
	{
		LOG.trace("User {} open WebSocket session {}",this.user.getLogin(), this.wsSession.getId());
		this.realtimeTopicProducer.send(this.jmsSession.createObjectMessage(json));
	}
	
	public void onRealtimeMessage(Message message)
	{
		try {
			if(this.wsSession.isOpen()) {
				RealtimeMessage realtimeMessage = message.getBody(RealtimeMessage.class);
				this.wsSession.getBasicRemote().sendText(this.objectMapper.writeValueAsString(this.mapper.realtimeMessageTorealtimeMessageDto(realtimeMessage)));
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
		RealtimeMessage<UserStatus> disconnectMessage = new RealtimeMessageBuilder<UserStatus>()
			.setSender(this.user.getId())
			.setChannel(UsersStatusService.CHANNEL)
			.setBody(UserStatus.DISCONNECTED)
			.createRealtimeMessage();
		this.realtimeTopicProducer.send(this.jmsSession.createObjectMessage(disconnectMessage));
		this.connection.close();
	}
	
	@OnError
	public void onError(Session session, Throwable t) throws JMSException
	{
		LOG.debug("User {} error WebSocket session {}",this.user.getLogin(), this.wsSession.getId());
	}
	
	
}
