package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.IssuesEndpoint;
import com.famaridon.redminengapi.rest.dto.IssueDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Arquillian.class)
public class IssuesEndpointImplTest extends AbstractEndpointlTest
{

	@Test
	public void findById(@ArquillianResteasyResource IssuesEndpoint issuesEndpoint) throws IOException
	{
		IssueDto issueDto = issuesEndpoint.findById(this.apiKey, 1L);
		Assert.assertEquals(new Long(1L), issueDto.getId());
	}
	
	@Test
	public void findAll(@ArquillianResteasyResource IssuesEndpoint issuesEndpoint) throws IOException
	{
		PageDto<IssueDto> issueDtoPageDto = issuesEndpoint.findAll(this.apiKey, 0L, 25L);
		Assert.assertTrue(issueDtoPageDto.getTotalCount() > 0);
	}
	
	@Test
	public void findByQueryAndProject(@ArquillianResteasyResource IssuesEndpoint issuesEndpoint) throws IOException
	{
		PageDto<IssueDto> issueDtoPageDto = issuesEndpoint.findByQueryAndProject(this.apiKey,1405L,372L, 0L, 25L);
		Assert.assertTrue(issueDtoPageDto.getTotalCount() > 0);
	}
}
