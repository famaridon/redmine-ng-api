package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.PrioritiesEndpoint;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.PriorityDto;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Arquillian.class)
public class PrioritiesEndpointImplTest extends AbstractEndpointlTest
{

	@Test
	public void findAll(@ArquillianResteasyResource PrioritiesEndpoint prioritiesEndpoint) throws IOException
	{
		PageDto<PriorityDto> priorityDtoPageDto = prioritiesEndpoint.findAll(this.apiKey);
		Assert.assertTrue(priorityDtoPageDto.getTotalCount() > 0);
	}
}
