package com.famaridon.redminengapi.rest.dto;


import com.famaridon.redminengapi.rest.refs.IterationRef;

public class ObjectiveDto extends AbstractDto {

    private String summary;
    private String description;
    private IterationRef iteration;

    public String getSummary()
    {
        return summary;
    }
    
    public void setSummary(String summary)
    {
        this.summary = summary;
    }
    
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
