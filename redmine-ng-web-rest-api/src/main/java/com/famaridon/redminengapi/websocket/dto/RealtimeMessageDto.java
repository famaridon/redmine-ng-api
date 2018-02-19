package com.famaridon.redminengapi.websocket.dto;

public class RealtimeMessageDto<T>
{
	private Long sender;
	private String channel;
	private T body;
	
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
