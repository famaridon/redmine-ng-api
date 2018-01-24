package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.StatusEndpoint;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import java.io.IOException;

@RequestScoped
public class StatusEndpointImpl extends AbstractRedmineEndpoint implements StatusEndpoint {
	
	@Override
	public Response findAll(String apiKey) throws IOException {
		return Response.ok(StatusEndpointImpl.class.getResourceAsStream("/status/status.json")).build();
	}
}