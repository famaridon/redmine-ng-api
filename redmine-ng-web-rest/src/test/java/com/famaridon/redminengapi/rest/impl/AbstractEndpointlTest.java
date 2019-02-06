package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import java.io.File;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public abstract class AbstractEndpointlTest {

  @EJB
  protected ConfigurationService configurationService;

  protected String apiKey;

  @Deployment
  public static WebArchive createDeployment() {
    File[] dependencies = Maven.resolver()
        .loadPomFromFile(new File("pom.xml"))
        .importRuntimeAndTestDependencies()
        .resolve().withTransitivity().asFile();

    return ShrinkWrap.create(WebArchive.class)
        .addPackages(true, "com.famaridon.redminengapi.rest")
        .addAsLibraries(dependencies)
        .addAsManifestResource("META-INF/MANIFEST.MF")
        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
        .addAsResource("config.json");

  }

  @Before
  public void setUp() throws Exception {
    this.apiKey = this.configurationService.getString("test.apiKey");
  }

}
