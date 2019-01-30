package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.ChartEndpoint;
import com.famaridon.redminengapi.rest.dto.BurndownChartDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.exceptions.ObjectNotFoundException;
import com.famaridon.redminengapi.services.indicators.BurndownChartService;
import com.famaridon.redminengapi.services.indicators.IterationService;
import com.famaridon.redminengapi.services.indicators.beans.BurndownChart;
import java.io.IOException;
import java.util.Optional;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@RequestScoped
public class ChartEndpointImpl extends AbstractRedmineEndpoint implements ChartEndpoint {

	@EJB
	private IterationService iterationService;

	@EJB
	private BurndownChartService burndownChartService;

	@Inject
	private DtoMapper mapper;


	@Override
	public BurndownChartDto findByIteration(Long iterationId) {

		try {
			Optional<BurndownChart> burndownChart = this.burndownChartService.findByIteration(iterationId);
			if(!burndownChart.isPresent()) {
				throw new NotFoundException("Burndown not found for iteration " + iterationId);
			}
			return this.mapper.burndownChartToBurndownChartDto(burndownChart.get());
		} catch (ObjectNotFoundException e) {
			throw new NotFoundException(e.getMessage(), e);
		}
	}
}