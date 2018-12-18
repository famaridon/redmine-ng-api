package com.famaridon.redminengapi.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class IterationDto extends AbstractDto
{
	private Date start;
	private Date end;
	
	public Date getStart()
	{
		return start;
	}
	
	public void setStart(Date start)
	{
		this.start = start;
	}
	
	public Date getEnd()
	{
		return end;
	}
	
	public void setEnd(Date end)
	{
		this.end = end;
	}
}
