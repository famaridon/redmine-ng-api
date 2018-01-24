package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.PrioritiesEndpoint;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import java.io.IOException;

@RequestScoped
public class PrioritiesEndpointImpl extends AbstractRedmineEndpoint implements PrioritiesEndpoint {
	
	@Override
	public Response findAll(String apiKey) throws IOException {
		return Response.ok(PrioritiesEndpointImpl.class.getResourceAsStream("/priorities/priorities.json")).build();
	}
}