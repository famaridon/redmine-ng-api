package com.famaridon.redminengapi.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    Response releaseNoteZip();

    @GET
    @Path("/releaseNote/pdf")
    Response releaseNotePdf();

    @GET
    @Path("/releaseNote/doc")
    Response releaseNoteDoc();
}
