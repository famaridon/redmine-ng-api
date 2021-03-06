package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.api.features.CacheControl;
import com.famaridon.redminengapi.rest.dto.UserDto;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface UsersEndpoint {

  @GET
  @Path("/current")
  @CacheControl(maxAge = 30, maxAgeUnit = TimeUnit.MINUTES, isPrivate = true)
  UserDto findCurrent(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey) throws IOException;

  @GET
  @Path("/{id}")
  @CacheControl(maxAge = 15, maxAgeUnit = TimeUnit.MINUTES)
  UserDto findById(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey, @PathParam("id") long id) throws IOException;

}
