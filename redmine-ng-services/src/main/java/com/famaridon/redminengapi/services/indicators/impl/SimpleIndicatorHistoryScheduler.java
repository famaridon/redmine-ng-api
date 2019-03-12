package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.indicators.IterationService;
import com.famaridon.redminengapi.services.indicators.SimpleIndicatorService;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.metrics.MetricFactory;
import com.famaridon.redminengapi.services.metrics.beans.UpgradeableGauge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * TODO : this is a draft class to start collecting data. this class is moovapps process team specific
 */
@Singleton
public class SimpleIndicatorHistoryScheduler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleIndicatorHistoryScheduler.class);
	
	@Inject
	private MetricFactory metricFactory;
	private Map<Long, UpgradeableGauge> gaugeMap;
	@Inject
	private ConfigurationService configurationService;
	@Inject
	private IterationService iterationService;
	@Inject
	private SimpleIndicatorService simpleIndicatorService;
	
	@PostConstruct
	private void init() {
		this.gaugeMap = new HashMap<>();
		gaugeMap.put(1225L, this.metricFactory.registerUpgradeableGauge("process_open_tickets_test"));
		gaugeMap.put(1224L, this.metricFactory.registerUpgradeableGauge("process_open_tickets_integration"));
		gaugeMap.put(1223L, this.metricFactory.registerUpgradeableGauge("process_open_tickets_dev"));
		gaugeMap.put(1231L, this.metricFactory.registerUpgradeableGauge("process_open_tickets_techcenter"));
	}
	
	@Schedule(second = "*/15", minute = "*", hour = "*")
	private void scheduled() {
		
		String apiKey = this.configurationService.getString("redmine.readonlyApiKey");
		Optional<Iteration> currentIteration = this.iterationService.findCurrent();
		currentIteration.ifPresent(iteration -> {
			gaugeMap.entrySet().forEach(entry -> {
				try {
					Long count = this.simpleIndicatorService.countOpenIssueByCategoryAndIteration(apiKey, entry.getKey(), iteration.getNumber());
					entry.getValue().update(count);
					LOGGER.debug("Gauge {} updated to {}", entry.getKey(), count);
				} catch (IOException e) {
					LOGGER.warn("Can't update gauge {}", entry.getKey(), e);
				}
			});
			
		});
		
	}
	

	
}
