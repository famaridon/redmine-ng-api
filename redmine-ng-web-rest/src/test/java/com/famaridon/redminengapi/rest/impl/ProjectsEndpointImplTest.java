package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.api.ProjectsEndpoint;
import com.famaridon.redminengapi.rest.dto.MembershipDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.ProjectDto;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Arquillian.class)
public class ProjectsEndpointImplTest extends AbstractEndpointlTest
{

	@Test
	public void findById(@ArquillianResteasyResource ProjectsEndpoint projectsEndpoint) throws IOException
	{
		ProjectDto projectDto = projectsEndpoint.findById(this.apiKey, 372L);
		Assert.assertEquals("moovapps-process-team", projectDto.getIdentifier());
	}
	
	@Test
	public void findAll(@ArquillianResteasyResource ProjectsEndpoint projectsEndpoint) throws IOException
	{
		PageDto<ProjectDto> projectDtoPageDto = projectsEndpoint.findAll(this.apiKey,0L, 25L);
		Assert.assertTrue(projectDtoPageDto.getTotalCount() > 0);
	}
	
	@Test
	public void findMembershipsById(@ArquillianResteasyResource ProjectsEndpoint projectsEndpoint) throws IOException
	{
		PageDto<MembershipDto> membershipDtoPageDto = projectsEndpoint.findMembershipsById(this.apiKey,372L, 0L, 25L);
		Assert.assertTrue(membershipDtoPageDto.getTotalCount() > 0);
	}
}
