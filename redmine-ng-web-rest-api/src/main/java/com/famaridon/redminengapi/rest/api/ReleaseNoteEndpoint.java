package com.famaridon.redminengapi.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Release note Endpoint Interface
 */
@Path("/apo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ReleaseNoteEndpoint {

    @GET
    @Path("/releaseNote/zip")
    Response releaseNoteZip(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey,
        @QueryParam("version") @DefaultValue("prob version") Long versionId,
        @Context SecurityContext securityContext);

    @GET
    @Path("/releaseNote/pdf")
    Response releaseNotePdf(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey,
        @QueryParam("version") @DefaultValue("prob version") Long versionId,
        @Context SecurityContext securityContext);

    @GET
    @Path("/releaseNote/doc")
    Response releaseNoteDoc(@HeaderParam(SecurityHeaders.X_REDMINE_API_KEY) String apiKey,
        @QueryParam("version") @DefaultValue("prob version") Long versionId,
        @Context SecurityContext securityContext);

}
