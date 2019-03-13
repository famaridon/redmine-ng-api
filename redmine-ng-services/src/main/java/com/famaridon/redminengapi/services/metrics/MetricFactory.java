package com.famaridon.redminengapi.services.metrics;

import com.famaridon.redminengapi.services.metrics.beans.UpgradeableGauge;
import org.apache.commons.lang3.Validate;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricType;
import org.eclipse.microprofile.metrics.annotation.RegistryType;

import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class MetricFactory {
	
	private static final String METRICS_PREFIX = "com_famaridon_redminengapi_metrics_";
	
	@Inject
	@RegistryType(type = MetricRegistry.Type.APPLICATION)
	private MetricRegistry metricRegistry;
	
	public <T extends Number> UpgradeableGauge<T> registerUpgradeableGauge(String name, T initialValue) {
		Validate.notNull(initialValue);
		Metadata m = new Metadata(METRICS_PREFIX + name, MetricType.GAUGE);
		return this.metricRegistry.register(m, new UpgradeableGauge<>(initialValue));
	}
	
}
