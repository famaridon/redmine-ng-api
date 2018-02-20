package com.famaridon.redminengapi.services.redmine;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.IOException;

@RunWith(Arquillian.class)
public class VersionsServiceITest extends AbstractServiceITest {

    @EJB
    private VersionsService versionsService;

    @Test
    void testFindAll() throws IOException
    {
        this.versionsService.findAll(this.apiKey, 372L);
    }
}
