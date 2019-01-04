package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.impl.DefaultIterationService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import java.util.Date;

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
        iterationService.findAll();
    }
    
    @Test
    public void createIteration(){
        Iteration iteration = new Iteration();
        iteration.setName("Test");
        iteration.setStart(new Date(946684800l));
        iteration.setEnd(new Date(946684801l));
        iteration = iterationService.create(iteration);
    }
    
//    @Test
//    public void create(){
//
//    }
//
//    @Test
//    public void findById(){
//
//    }
//
//    @Test
//    public void update(){
//
//    }
    
}