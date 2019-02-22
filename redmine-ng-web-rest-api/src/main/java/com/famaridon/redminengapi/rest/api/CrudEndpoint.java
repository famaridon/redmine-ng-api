package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.AbstractDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import java.io.IOException;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

public interface CrudEndpoint<D extends AbstractDto> {

  @GET
  PageDto<D> findAll(
      @QueryParam(PagerParam.OFFSET) @DefaultValue("0") Long offset,
      @QueryParam(PagerParam.LIMIT) @DefaultValue("25") Long limit) throws IOException;

  @POST
  Response create(D d);

  @GET
  @Path("/{id : \\d+}")
  D findById(@PathParam("id") Long id);

  @PUT
  @Path("/{id}")
  Response update(@PathParam("id") Long id, D dto);

  @DELETE
  @Path("/{id}")
  void deleteById(@PathParam("id") Long id);
}
