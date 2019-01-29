package com.famaridon.redminengapi.rest.dto;


import com.famaridon.redminengapi.rest.refs.IterationRef;

public class ObjectiveDto extends AbstractDto {

    private String description;
    private IterationRef iteration;
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }

    public IterationRef getIteration() {
        return iteration;
    }

    public void setIteration(IterationRef iteration) {
        this.iteration = iteration;
    }
}
