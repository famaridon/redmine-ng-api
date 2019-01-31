package com.famaridon.redminengapi.rest.api;

import com.famaridon.redminengapi.rest.dto.BurndownChartDto;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/chart")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ChartEndpoint {

	@GET
	@Path("/burndown/{iterationId}")
	public BurndownChartDto findBurndownByIteration(@PathParam("iterationId") Long iterationId) throws IOException;

	@GET
	@Path("/burndown/{iterationId}/ideal")
	public BurndownChartDto buildIdealBurndown(@PathParam("iterationId") Long iterationId);


}