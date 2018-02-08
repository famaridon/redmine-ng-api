package com.famaridon.redminengapi.websocket;

import com.famaridon.redminengapi.services.redmine.UserService;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;

@ServerEndpoint(value = "/ws/issues",configurator = RedmineAPIKeyConfigurator.class)
public class RedmineNgApiWebSocket
{
	private Map<String, Object> userProperties;
	
	@Inject
	private UserService userService;
	
	@OnOpen
	public void traiterOnOpen (Session session, EndpointConfig config) throws IOException
	{
		System.out.println ("WebSocket ouverte : "+session.getId());
	}
	
	@OnMessage
	public void traiterOnMessage(String message) {
		System.out.println("Message recu par WebSocket : "+message);
	}
	
	@OnClose
	public void traiterOnClose (CloseReason reason) {
		System.out.println("Fermeture de la WebSocket a cause de : "+reason.getReasonPhrase());
	}
	
	@OnError
	public void onError(Session session, Throwable t) {
		t.printStackTrace();
	}
}
