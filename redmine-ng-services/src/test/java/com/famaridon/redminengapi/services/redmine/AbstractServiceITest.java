package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.configuration.MockConfigurationService;
import com.famaridon.redminengapi.services.redmine.filter.AbstractFilter;
import com.famaridon.redminengapi.services.redmine.impl.AbstractRedmineService;
import junit.framework.Assert;
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
    public static final String[] REDMINE_CLIENT_DEPENDENCIES = new String[]{
            "org.apache.commons:commons-lang3",
            "org.apache.httpcomponents:fluent-hc",
            "com.fasterxml.jackson.core:jackson-databind",
            "org.jsoup:jsoup"
    };

    public static WebArchive prepareDeployment() {
        File[] dependencies = Maven.resolver()
                .loadPomFromFile(new File("pom.xml"))
                .resolve(REDMINE_CLIENT_DEPENDENCIES)
                .withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addClass(AbstractServiceITest.class)
                .addClass(Pager.class)
                .addClass(FilterFactory.class)
                .addClass(Filter.class)
                .addClass(QueryParamSerializable.class)
                .addClass(AbstractRedmineService.class)
                .addClass(ConfigurationService.class)
                .addClass(MockConfigurationService.class)
                .addPackages(true, AbstractFilter.class.getPackage())
                .addPackages(true, "com.famaridon.redminengapi.services.redmine.rest.client")
                .addAsLibraries(dependencies)
                // add custom MANIFEST.MF to load infinispan module
                .addAsManifestResource(new File("src/test/resources/META-INF/MANIFEST.MF"))
                // add web.xml to tell wildfly to start infinispan clustering
                .addAsWebInfResource(new File("src/test/resources/WEB-INF/web.xml"))
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("config.json");

    }

    @Before
    public void setUp() {
        this.apiKey = System.getenv("TEST_REDMINE_API_KEY");
        Assert.assertNotNull(this.apiKey);
    }

}
