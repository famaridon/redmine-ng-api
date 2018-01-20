package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.UserDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface UsersEndpoint {
	
	@GET
	@Path("/current")
	public UserDto findCurrent(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey);

}
