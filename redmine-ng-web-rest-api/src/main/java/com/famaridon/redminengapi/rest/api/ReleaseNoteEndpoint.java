package com.famaridon.redminengapi.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Release note Endpoint Interface
 */
@Path("/apo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ReleaseNoteEndpoint {

    @GET
    @Path("/releaseNote/zip")
    Response releaseNoteZip(@QueryParam("version") @DefaultValue("prob version") String version,
        @QueryParam("product") @DefaultValue("prob product") String product);

    @GET
    @Path("/releaseNote/pdf")
    Response releaseNotePdf(@QueryParam("version") @DefaultValue("probv") String version, @QueryParam("product") @DefaultValue("probp") String product);

    @GET
    @Path("/releaseNote/doc")
    Response releaseNoteDoc(@QueryParam("version") @DefaultValue("probv") String version, @QueryParam("product") @DefaultValue("probp") String product);

    @GET
    @Path("/releaseNote/version")
    Response releaseNoteVersion();
}
