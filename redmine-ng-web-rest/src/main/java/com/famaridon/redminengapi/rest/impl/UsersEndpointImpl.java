package com.famaridon.redminengapi.rest.impl;

import com.famaridon.redminengapi.rest.AbstractRedmineEndpoint;
import com.famaridon.redminengapi.rest.api.UsersEndpoint;
import com.famaridon.redminengapi.rest.dto.UserDto;
import com.famaridon.redminengapi.rest.mapper.DtoMapper;
import com.famaridon.redminengapi.services.redmine.UserService;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UsersEndpointImpl extends AbstractRedmineEndpoint implements UsersEndpoint {

  @Inject
  private UserService userService;

  @Inject
  private DtoMapper mapper;

  @Override
  public UserDto findCurrent(String apiKey) throws IOException {
    UserDto userDto = this.mapper.userToUserDto(this.userService.findCurrent(apiKey));
    userDto.setRoles(this.userService.findRoles(userDto.getLogin()));
    return userDto;
  }

  @Override
  public UserDto findById(String apiKey, long id) throws IOException {
    return this.mapper.userToUserDto(this.userService.findById(apiKey, id));
  }
}