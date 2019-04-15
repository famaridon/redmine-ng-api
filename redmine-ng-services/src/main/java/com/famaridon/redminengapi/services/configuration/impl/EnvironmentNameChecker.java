package com.famaridon.redminengapi.services.configuration.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class EnvironmentNameChecker {
	
	private static final Logger LOG = LoggerFactory.getLogger(EnvironmentNameChecker.class);
	public static final String PATH_SAFE_REGEXP = "^[a-zA-Z0-9]++$";
	
	public EnvironmentNameChecker() {
	}
	
	Optional<String> validate(String profile) {
		if (!profile.matches(PATH_SAFE_REGEXP)) {
			LOG.warn("profile must only contains letters and digits '{}'", profile);
			return Optional.empty();
		}
		return Optional.of(profile);
	}
}