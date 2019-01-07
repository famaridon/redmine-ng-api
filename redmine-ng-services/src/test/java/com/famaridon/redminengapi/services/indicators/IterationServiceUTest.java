package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.impl.DefaultIterationService;
import com.famaridon.redminengapi.services.redmine.Pager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.time.LocalDate;
import java.util.Date;

public class IterationServiceUTest extends AbstractIndicatorsServiceUTest {

//    @EJB
    protected IterationService iterationService;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return prepareDeployment()
                .addClass(IterationServiceUTest.class)
                .addClass(IterationService.class)
                .addClass(DefaultIterationService.class);
    }

//    @Test
    public void findAll() {
        iterationService.findAll(new Pager());
    }
    
//    @Test
    public void createIteration(){
        Iteration iteration = new Iteration();
        iteration.setName("Test");
        iteration.setStart( LocalDate.now());
        iteration.setEnd(LocalDate.now());
        iteration = iterationService.create(iteration);
    }
    
}