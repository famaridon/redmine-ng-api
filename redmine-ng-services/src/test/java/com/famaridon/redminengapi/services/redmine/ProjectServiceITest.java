package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.impl.DefaultConfigurationService;
import com.famaridon.redminengapi.services.redmine.impl.DefaultProjectService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.File;
import java.io.IOException;

@RunWith(Arquillian.class)
public class ProjectServiceITest {
	
	@EJB
	ConfigurationService configurationService;
	
	@EJB
	ProjectService projectService;
	
	String testApiKey;
	
	@Deployment
	public static WebArchive createDeployment() {
		File[] dependencies = Maven.resolver()
		.loadPomFromFile(new File("pom.xml"))
		.importRuntimeAndTestDependencies()
		.resolve().withTransitivity().asFile();
		
		return ShrinkWrap.create(WebArchive.class, "redmine-ng-api.war")
			.addPackages(true,"com.famaridon.redminengapi.services.redmine")
			.addClass(ConfigurationService.class)
			.addClass(DefaultConfigurationService.class)
			.addClass(ProjectService.class)
			.addClass(DefaultProjectService.class)
			.addAsLibraries(dependencies)
			.setManifest( "META-INF/MANIFEST.MF")
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
			.addAsResource("config.json");

	}
	
	@Before
	public void setUp() throws Exception {
		this.testApiKey = this.configurationService.getString("test.apiKey");
		this.projectService = new DefaultProjectService(this.configurationService);
	}
	
	@Test
	public void findById() throws IOException {
		this.projectService.findById(this.testApiKey, 372L);
	}
	
	@Test
	public void findAll() throws IOException {
		this.projectService.findAll(this.testApiKey, new Pager());
	}
}
