package com.famaridon.redminengapi.websocket.dto;

public class MessageDto<T>
{
	private String topic;
	private T content;
	
	public String getTopic()
	{
		return this.topic;
	}
	
	public void setTopic(String topic)
	{
		this.topic = topic;
	}
	
	public T getContent()
	{
		return this.content;
	}
	
	public void setContent(T content)
	{
		this.content = content;
	}
}
