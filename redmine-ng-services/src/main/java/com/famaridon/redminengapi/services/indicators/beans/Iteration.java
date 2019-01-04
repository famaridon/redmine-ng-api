package com.famaridon.redminengapi.services.indicators.beans;

import java.time.LocalDate;

public class Iteration extends AbstractIndicatorBean
{
	private LocalDate start;
	private LocalDate end;
	
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
}
