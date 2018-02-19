package com.famaridon.redminengapi.services.realtime.beans;

import org.apache.commons.lang3.Validate;

import java.io.Serializable;

public class RealtimeMessage<T extends Serializable> implements Serializable
{
	private Long sender;
	private String channel;
	private T body;
	
	public RealtimeMessage(Long sender, String channel, T body)
	{
		Validate.notNull(sender);
		this.sender = sender;
		Validate.notNull(channel);
		this.channel = channel;
		Validate.notNull(body);
		this.body = body;
	}
	
	public Long getSender()
	{
		return this.sender;
	}
	
	public void setSender(Long sender)
	{
		this.sender = sender;
	}
	
	public String getChannel()
	{
		return this.channel;
	}
	
	public void setChannel(String channel)
	{
		this.channel = channel;
	}
	
	public T getBody()
	{
		return this.body;
	}
	
	public void setBody(T body)
	{
		this.body = body;
	}
}
