package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.StatusEndpoint;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.StatusDto;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Arquillian.class)
public class StatusEndpointImplTest extends AbstractEndpointlTest
{
	
	@Test
	public void findAvailableByTracker(@ArquillianResteasyResource StatusEndpoint statusEndpoint) throws IOException
	{
		PageDto<StatusDto> statusPageDto = statusEndpoint.findAvailableByTracker(this.apiKey, 35L);
		Assert.assertTrue(statusPageDto.getTotalCount() > 0);
	}
	
	@Test
	public void findAvailableByTrackerAndStatus(@ArquillianResteasyResource StatusEndpoint statusEndpoint) throws IOException
	{
		PageDto<StatusDto> statusPageDto = statusEndpoint.findAvailableByTrackerAndStatus(this.apiKey, 35L, 9L);
		Assert.assertTrue(statusPageDto.getTotalCount() > 0);
	}
}
