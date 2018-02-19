package com.famaridon.redminengapi.services.realtime.beans;

import java.io.Serializable;

public class RealtimeMessageBuilder<T extends Serializable>
{
	private Long sender;
	private String channel;
	private T body;
	
	public RealtimeMessageBuilder<T> setSender(Long sender)
	{
		this.sender = sender;
		return this;
	}
	
	public RealtimeMessageBuilder<T> setChannel(String channel)
	{
		this.channel = channel;
		return this;
	}
	
	public RealtimeMessageBuilder<T> setBody(T body)
	{
		this.body = body;
		return this;
	}
	
	public RealtimeMessage<T> createRealtimeMessage()
	{
		return new RealtimeMessage<>(this.sender, this.channel, this.body);
	}
}