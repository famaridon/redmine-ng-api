package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.indicators.impl.AbstractCrudRepositoryService;
import java.io.File;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

public abstract class AbstractIndicatorsServiceUTest {

    public static final String[] REDMINE_CLIENT_DEPENDENCIES = new String[]{
            "org.apache.commons:commons-lang3"
    };

    public static WebArchive prepareDeployment() {
        File[] dependencies = Maven.resolver()
                .loadPomFromFile(new File("pom.xml"))
                .resolve(REDMINE_CLIENT_DEPENDENCIES)
                .withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addClass(AbstractIndicatorsServiceUTest.class)
                .addClass(CrudService.class)
                .addClass(AbstractCrudRepositoryService.class)
                .addAsLibraries(dependencies)
                // add custom MANIFEST.MF to load infinispan module
                .addAsManifestResource(new File("src/test/resources/META-INF/MANIFEST.MF"))
                // add web.xml to tell wildfly to start infinispan clustering
                .addAsWebInfResource(new File("src/test/resources/WEB-INF/web.xml"))
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

    }


}
