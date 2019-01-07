package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.IterationDto;
import com.famaridon.redminengapi.rest.dto.PageDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/iteration")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IterationEndpoint {

  @GET
  public PageDto<IterationDto> findAll(
      @QueryParam(PagerParam.OFFSET) @DefaultValue("0") Long offset,
      @QueryParam(PagerParam.LIMIT) @DefaultValue("25") Long limit) throws IOException;

  @POST
  public IterationDto create(IterationDto iterationDto);

  @GET
  @Path("/current")
  public IterationDto findCurrent();

  @GET
  @Path("/{id : \\d+}")
  public IterationDto findById(@PathParam("id") Long id);

  @PUT
  @Path("/{id}")
  public void update(@PathParam("id") Long id, IterationDto iterationDto);
}
