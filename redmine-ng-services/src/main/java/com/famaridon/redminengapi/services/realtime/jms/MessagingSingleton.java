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
	
	@Resource(lookup="java:/jms/redmine-ng-api/topic/users")
	private Topic usersTopic;
	
	@Resource(lookup="java:/jms/redmine-ng-api/topic/issues")
	private Topic issuesTopic;
	
	@Produces @NamedTopic("users")
	public Topic getUsersTopic()
	{
		return this.usersTopic;
	}
	
	@Produces @NamedTopic("issues")
	public Topic getIssuesTopic()
	{
		return this.issuesTopic;
	}
	
	@Produces
	public Connection createConnection() throws JMSException
	{
		return this.factory.createConnection();
	}
}
