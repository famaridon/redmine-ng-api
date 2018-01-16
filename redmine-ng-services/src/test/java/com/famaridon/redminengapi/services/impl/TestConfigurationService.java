package com.famaridon.redminengapi.services.impl;

import com.famaridon.redminengapi.services.ConfigurationService;

import java.net.URL;

public class TestConfigurationService extends DefaultConfigurationService implements ConfigurationService {
	
	public TestConfigurationService() {
		this.init();
	}
	
	@Override
	protected URL getConfigurationFile() {
		return DefaultConfigurationService.class.getResource("/config-test.properties");
	}
}
