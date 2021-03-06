package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.mock.TestRedmineClientConfiguration;
import org.junit.Before;

import static org.junit.Assert.assertNotNull;

public abstract class AbstractServiceITest {


//  public static final String[] REDMINE_CLIENT_DEPENDENCIES = new String[]{
//      "org.apache.commons:commons-lang3",
//      "org.apache.httpcomponents:fluent-hc",
//      "com.fasterxml.jackson.core:jackson-databind",
//      "org.jsoup:jsoup"
//  };
  protected String apiKey;
  protected RedmineClientConfiguration redmineClientConfiguration;

//  public static WebArchive prepareDeployment() {
//    File[] dependencies = Maven.resolver()
//        .loadPomFromFile(new File("pom.xml"))
//        .resolve(REDMINE_CLIENT_DEPENDENCIES)
//        .withTransitivity().asFile();
//
//    return ShrinkWrap.create(WebArchive.class)
//        .addClass(AbstractServiceITest.class)
//        .addClass(Pager.class)
//        .addClass(Filter.class)
//        .addClass(FilterFactory.class)
//        .addClass(IssueAssociatedData.class)
//        .addClass(QueryParamSerializable.class)
//        .addClass(AbstractRedmineService.class)
//        .addClass(RedmineClientConfiguration.class)
//        .addClass(MockRedmineClientConfiguration.class)
//        .addPackages(true, "com.famaridon.redminengapi.services.redmine.rest.client")
//        .addAsLibraries(dependencies)
//        .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
//
//  }
  
  @Before
  public void setUp() {
    this.apiKey = System.getenv("TEST_REDMINE_API_KEY");
    assertNotNull(this.apiKey);
    this.redmineClientConfiguration = new TestRedmineClientConfiguration();
  }

}
