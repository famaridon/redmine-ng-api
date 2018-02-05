package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.impl.DefaultConfigurationService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;

import javax.ejb.EJB;
import java.io.File;

public abstract class AbstractServiceITest {
	
	@EJB
	protected ConfigurationService configurationService;

	protected String apiKey;
	
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
			.addAsLibraries(dependencies)
			.setManifest( "META-INF/MANIFEST.MF")
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
			.addAsResource("config.json");

	}
	
	@Before
	public void setUp() throws Exception {
		this.apiKey = this.configurationService.getString("test.apiKey");
	}
	
}
