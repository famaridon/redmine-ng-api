package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.IssueContextDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/context")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IssueContextEndpoint extends CrudEndpoint<IssueContextDto> {

  @GET
  @Path("/scope/{id}")
  PageDto<IssueContextDto> findAllByScopeId(@PathParam("id") Long scopeId,
      @QueryParam(PagerParam.OFFSET) @DefaultValue("0") Long offset,
      @QueryParam(PagerParam.LIMIT) @DefaultValue("25") Long limit);

}
