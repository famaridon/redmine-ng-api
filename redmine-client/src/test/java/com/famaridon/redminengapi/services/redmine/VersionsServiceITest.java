package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.DefaultVersionsService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.IOException;

@RunWith(Arquillian.class)
public class VersionsServiceITest extends AbstractServiceITest {

    @EJB
    private VersionsService versionsService;

    @Deployment
    public static WebArchive createDeployment() {
        return prepareDeployment()
                .addClass(VersionsServiceITest.class)
                .addClass(VersionsService.class)
                .addClass(DefaultVersionsService.class);
    }
    @Test
    public void testFindAll() throws IOException
    {
        this.versionsService.findAll(this.apiKey, 372L);
    }
}
