package com.famaridon.redminengapi.rest.dto;


public class ObjectiveDto extends AbstractDto {

    private String summary;
    private String description;
    
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
}
