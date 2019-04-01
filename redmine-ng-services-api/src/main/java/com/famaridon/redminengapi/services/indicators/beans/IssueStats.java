package com.famaridon.redminengapi.services.indicators.beans;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class IssueStats {

	private Map<Long, Duration> durationByTracker = new HashMap<>();
	
	public Map<Long, Duration> getDurationByTracker() {
		return durationByTracker;
	}
	
	public void setDurationByTracker(Map<Long, Duration> durationByTracker) {
		this.durationByTracker = durationByTracker;
	}
}
