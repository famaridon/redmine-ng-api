package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.impl.TestConfigurationService;
import com.famaridon.redminengapi.services.redmine.impl.DefaultProjectService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ProjectServiceITest {
	
	ConfigurationService configurationService;
	ProjectService projectService;
	String testApiKey;
	
	@Before
	public void setUp() throws Exception {
		this.configurationService = new TestConfigurationService();
		this.testApiKey = this.configurationService.getString("redmine.server.test.apiKey");
		this.projectService = new DefaultProjectService(this.configurationService);
	}
	
	@Test
	public void findById() throws IOException {
		this.projectService.findById(this.testApiKey, 372L);
	}
	
	@Test
	public void findAll() throws IOException {
		this.projectService.findAll(this.testApiKey);
	}
}
