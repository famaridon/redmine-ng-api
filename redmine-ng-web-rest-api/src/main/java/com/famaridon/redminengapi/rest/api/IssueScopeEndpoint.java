package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.IssueScopeDto;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/scope")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface IssueScopeEndpoint extends CrudEndpoint<IssueScopeDto> {

}
