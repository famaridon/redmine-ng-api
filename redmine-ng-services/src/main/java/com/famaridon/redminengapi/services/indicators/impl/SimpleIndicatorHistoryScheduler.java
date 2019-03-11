package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.indicators.IterationService;
import com.famaridon.redminengapi.services.indicators.SimpleIndicatorService;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import org.eclipse.microprofile.metrics.Gauge;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;
import org.eclipse.microprofile.metrics.annotation.RegistryType;
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
	@RegistryType(type = MetricRegistry.Type.APPLICATION)
	private MetricRegistry metricRegistry;
	private Map<Long, NumberGauge> gaugeMap;
	@Inject
	private ConfigurationService configurationService;
	@Inject
	private IterationService iterationService;
	@Inject
	private SimpleIndicatorService simpleIndicatorService;
	
	@PostConstruct
	private void init() {
		this.gaugeMap = new HashMap<>();
		initGauge(1225L, "process_open_tickets_test");
		initGauge(1224L, "process_open_tickets_integration");
		initGauge(1223L, "process_open_tickets_dev");
		initGauge(1231L, "process_open_tickets_techcenter");
	}
	
	private void initGauge(Long id, String name) {
		Metadata m = new Metadata(name, MetricType.GAUGE);
		NumberGauge gauge = this.metricRegistry.register(m, new NumberGauge(null));
		this.gaugeMap.put(id, gauge);
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
	
	private class NumberGauge implements Gauge<Long> {
		
		private Long number;
		
		protected NumberGauge(Long number) {
			this.number = number;
		}
		
		@Override
		public Long getValue() {
			return this.number;
		}
		
		public void update(Long number) {
			this.number = number;
		}
	}
	
}
