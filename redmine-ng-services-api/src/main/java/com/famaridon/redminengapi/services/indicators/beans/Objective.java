package com.famaridon.redminengapi.services.indicators.beans;

public class Objective extends AbstractBean {

    private String description;
    private Iteration iteration;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Iteration getIteration() {
        return iteration;
    }

    public void setIteration(Iteration iteration) {
        this.iteration = iteration;
    }
}
