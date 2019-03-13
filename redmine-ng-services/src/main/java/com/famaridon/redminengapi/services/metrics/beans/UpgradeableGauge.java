package com.famaridon.redminengapi.services.metrics.beans;

import org.eclipse.microprofile.metrics.Gauge;

public class UpgradeableGauge<T extends Number> implements Gauge<T> {
	
	private T number;
	
	public UpgradeableGauge(T initialValue) {
		this.number = initialValue;
	}
	
	@Override
	public T getValue() {
		return this.number;
	}
	
	public void update(T number) {
		this.number = number;
	}
}