package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.UsersEndpoint;
import com.famaridon.redminengapi.rest.dto.UserDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.redmine.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UsersEndpointImpl extends AbstractRedmineEndpoint implements UsersEndpoint {
	
	@EJB
	private UserService userService;
	
	@Inject
	private DtoMapper mapper;
	
	@Override
	public UserDto findCurrent(String apiKey) {
		return this.mapper.userToUserDto(this.userService.findCurrent(apiKey));
	}
}