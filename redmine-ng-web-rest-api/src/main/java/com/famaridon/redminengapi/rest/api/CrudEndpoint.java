package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.AbstractDto;
import com.famaridon.redminengapi.rest.dto.IterationDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface CrudEndpoint<DTO extends AbstractDto> {

  @GET
  public PageDto<DTO> findAll(
      @QueryParam(PagerParam.OFFSET) @DefaultValue("0") Long offset,
      @QueryParam(PagerParam.LIMIT) @DefaultValue("25") Long limit) throws IOException;

  @POST
  public Response create(DTO dto);

  @GET
  @Path("/{id : \\d+}")
  public DTO findById(@PathParam("id") Long id);

  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") Long id, DTO dto);

  @DELETE
  @Path("/{id}")
  public void deleteById(@PathParam("id") Long id);
}
