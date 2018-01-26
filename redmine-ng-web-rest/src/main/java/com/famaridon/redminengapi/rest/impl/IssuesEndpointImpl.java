package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.IssuesEndpoint;
import com.famaridon.redminengapi.rest.dto.IssueDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.redmine.IssueService;
import org.apache.commons.lang3.NotImplementedException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;

@RequestScoped
public class IssuesEndpointImpl extends AbstractRedmineEndpoint implements IssuesEndpoint {

	@EJB
	private IssueService issueService;

	@Inject
	private DtoMapper mapper;
	
	@Override
	public PageDto<IssueDto> findAll(String apiKey) throws IOException {
		throw new NotImplementedException("");
	}
	
	@Override
	public IssueDto findById(String apiKey, Long id) throws IOException {
		throw new NotImplementedException("");
	}
	
	@Override
	public PageDto<IssueDto> findByQuery(String apiKey, Long query, Long offset, Long limit) throws IOException {
		throw new NotImplementedException("");
	}
	
	@Override
	public PageDto<IssueDto> findByQueryAndProject(String apiKey, Long query, Long project, Long offset, Long limit) throws IOException {
		throw new NotImplementedException("");
	}
}