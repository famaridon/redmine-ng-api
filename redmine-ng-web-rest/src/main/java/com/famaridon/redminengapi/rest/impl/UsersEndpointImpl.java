package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.UsersEndpoint;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.UserDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.realtime.beans.UserStatus;
import com.famaridon.redminengapi.services.redmine.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@RequestScoped
public class UsersEndpointImpl extends AbstractRedmineEndpoint implements UsersEndpoint {
	
	@EJB
	private UserService userService;
	
	@Inject
	private DtoMapper mapper;
	
	@Override
	public UserDto findCurrent(String apiKey) throws IOException {
		return this.mapper.userToUserDto(this.userService.findCurrent(apiKey));
	}
	
	@Override
	public UserDto findById(String apiKey, long id) throws IOException {
		return this.mapper.userToUserDto(this.userService.findById(apiKey, id));
	}
	
	@Override
	public PageDto<Long> findConnected(String apiKey) throws IOException
	{
		Set<Long> users = this.userService.getUserByUsersStatus(UserStatus.CONNECTED);
		PageDto<Long> page = new PageDto<>();
		page.setOffset(0);
		page.setLimit(users.size());
		page.setTotalCount(users.size());
		page.setElements(new ArrayList<>(users));
		return page;
	}
}