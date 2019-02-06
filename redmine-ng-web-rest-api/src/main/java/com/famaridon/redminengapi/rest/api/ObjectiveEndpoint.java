package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.ObjectiveDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/objective")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ObjectiveEndpoint extends CrudEndpoint<ObjectiveDto> {

  @GET
  @Path("/iteration/{id}")
  PageDto<ObjectiveDto> findAllByIterationId(@PathParam("id") Long iterationId,
      @QueryParam(PagerParam.OFFSET) @DefaultValue("0") Long offset,
      @QueryParam(PagerParam.LIMIT) @DefaultValue("25") Long limit);

}
