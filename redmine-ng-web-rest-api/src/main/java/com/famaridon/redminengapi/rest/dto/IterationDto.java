package com.famaridon.redminengapi.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;

public class IterationDto extends AbstractDto
{
	private LocalDate start;
	private LocalDate end;
	private Long number;
	
	public LocalDate getStart()
	{
		return start;
	}
	
	public void setStart(LocalDate start)
	{
		this.start = start;
	}
	
	public LocalDate getEnd()
	{
		return end;
	}
	
	public void setEnd(LocalDate end)
	{
		this.end = end;
	}
	
	public Long getNumber() {
		return number;
	}
	
	public void setNumber(Long number) {
		this.number = number;
	}
}
