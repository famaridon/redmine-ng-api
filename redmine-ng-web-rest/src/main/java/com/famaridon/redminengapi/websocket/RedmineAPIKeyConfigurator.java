package com.famaridon.redminengapi.websocket;

import com.famaridon.redminengapi.rest.api.SecurityHeaders;
import com.famaridon.redminengapi.services.redmine.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RedmineAPIKeyConfigurator extends ServerEndpointConfig.Configurator
{

	private static final Logger LOG = LoggerFactory.getLogger(RedmineAPIKeyConfigurator.class);

	@Inject
	private UserService userService;
	
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response)
	{
		super.modifyHandshake(sec, request, response);
		List<String> paramList = request.getParameterMap().get(SecurityHeaders.X_REDMINE_API_KEY);
		if(paramList == null || paramList.isEmpty()) {
			LOG.warn("WebSocket handshake without " + SecurityHeaders.X_REDMINE_API_KEY);
			response.getHeaders().put(HandshakeResponse.SEC_WEBSOCKET_ACCEPT, new ArrayList<String>());
			return;
		}
		
		try {
			this.userService.findCurrent(paramList.get(0));
		}
		catch (IOException e) {
			LOG.warn("Can't validate handshake " + SecurityHeaders.X_REDMINE_API_KEY);
			LOG.debug("Redmine api key validation failed!", e);
			response.getHeaders().put(HandshakeResponse.SEC_WEBSOCKET_ACCEPT, new ArrayList<String>());
		}
	}
}
