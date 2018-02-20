package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.VersionsEndpoint;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.VersionDto;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class VersionsEndpointTest extends AbstractEndpointlTest
{
	@Test
	public void findCurrent(@ArquillianResteasyResource VersionsEndpoint versionsEndpoint) throws IOException
	{
		PageDto<VersionDto> pageDto = versionsEndpoint.findByProject(this.apiKey, 372L);
		Assert.assertTrue(pageDto.getTotalCount() > 0);
		Assert.assertTrue(pageDto.getElements().size() > 0);
	}
}