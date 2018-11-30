package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.indicators.impl.DefaultIterationService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class IterationServiceUTest extends AbstractIndicatorsServiceUTest {

    @EJB
    protected IterationService iterationService;

    @Deployment
    public static WebArchive createDeployment() {
        return prepareDeployment()
                .addClass(IterationServiceUTest.class)
                .addClass(IterationService.class)
                .addClass(DefaultIterationService.class);
    }

    @Test
    public void findAll() {
    }
}