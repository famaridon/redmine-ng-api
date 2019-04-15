package com.famaridon.redminengapi.services.configuration.impl;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class EnvironmentNameCheckerUTest {
	
	@Test
	public void valid() {
		EnvironmentNameChecker environmentNameChecker = new EnvironmentNameChecker();
		Optional<String> profile = environmentNameChecker.validate("develop");
		assertTrue(profile.isPresent());
	}
	
	@Test
	public void notValid() {
		EnvironmentNameChecker environmentNameChecker = new EnvironmentNameChecker();
		Optional<String> profile = environmentNameChecker.validate("/../../develop.json");
		assertFalse(profile.isPresent());
	}
	
}