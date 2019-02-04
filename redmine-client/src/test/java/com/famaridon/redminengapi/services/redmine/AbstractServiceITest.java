package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.AbstractRedmineService;
import com.famaridon.redminengapi.services.redmine.mock.MockRedmineClientConfiguration;
import junit.framework.Assert;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;

import java.io.File;

public abstract class AbstractServiceITest {
    

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
                .addClass(Filter.class)
                .addClass(QueryParamSerializable.class)
                .addClass(AbstractRedmineService.class)
                .addClass(RedmineClientConfiguration.class)
                .addClass(MockRedmineClientConfiguration.class)
                .addPackages(true, "com.famaridon.redminengapi.services.redmine.rest.client")
                .addAsLibraries(dependencies)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

    }

    @Before
    public void setUp() {
        this.apiKey = System.getenv("TEST_REDMINE_API_KEY");
        Assert.assertNotNull(this.apiKey);
    }

}
