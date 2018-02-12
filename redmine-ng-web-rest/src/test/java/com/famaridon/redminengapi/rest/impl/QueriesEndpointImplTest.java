package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.QueriesEndpoint;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.QueryDto;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Arquillian.class)
public class QueriesEndpointImplTest extends AbstractEndpointlTest
{

	@Test
	public void findAll(@ArquillianResteasyResource QueriesEndpoint queriesEndpoint) throws IOException
	{
		PageDto<QueryDto> queryDtoPageDto = queriesEndpoint.findAll(this.apiKey, 0L, 25L);
		Assert.assertTrue(queryDtoPageDto.getTotalCount() > 0);
	}
}
