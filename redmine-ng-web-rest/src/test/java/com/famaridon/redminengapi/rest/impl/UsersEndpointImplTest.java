package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.UsersEndpoint;
import com.famaridon.redminengapi.rest.dto.UserDto;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Arquillian.class)
public class UsersEndpointImplTest extends AbstractEndpointlTest
{
	
	@Test
	public void findCurrent(@ArquillianResteasyResource UsersEndpoint usersEndpoint) throws IOException
	{
		UserDto userDto = usersEndpoint.findCurrent(this.apiKey);
		Assert.assertNotNull(userDto.getLogin());
	}
	
	@Test
	public void findById(@ArquillianResteasyResource UsersEndpoint usersEndpoint) throws IOException
	{
		UserDto userDto = usersEndpoint.findById(this.apiKey, 5);
		Assert.assertEquals("famaridon", userDto.getLogin());
	}
}
