package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.IterationDto;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/iteration")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IterationEndpoint extends CrudEndpoint<IterationDto> {

  @GET
  @Path("/current")
  IterationDto findCurrent();

}
