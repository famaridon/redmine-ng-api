package com.famaridon.redminengapi.services.realtime.jms;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Topic;

@Singleton
public class MessagingSingleton
{
	
	@Resource(lookup="java:/ConnectionFactory")
	private ConnectionFactory factory;
	
	@Resource(lookup="java:/jms/redmine-ng-api/topic/realtime")
	private Topic realtimeTopic;
	
	@Produces @NamedTopic("realtime")
	public Topic getIssuesTopic()
	{
		return this.realtimeTopic;
	}
	
	@Produces
	public Connection createConnection() throws JMSException
	{
		return this.factory.createConnection();
	}
}
