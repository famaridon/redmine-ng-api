package com.famaridon.redminengapi.rest.dto;

import java.util.ArrayList;
import java.util.List;

public class MembershipDto extends AbstractDto {

	private ProjectDto project;
	private UserDto user;
	private GroupDto group;
	private List<RoleDto> roles = new ArrayList<>();
	
	public ProjectDto getProject() {
		return this.project;
	}
	
	public void setProject(ProjectDto project) {
		this.project = project;
	}
	
	public UserDto getUser() {
		return this.user;
	}
	
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	public GroupDto getGroup() {
		return this.group;
	}
	
	public void setGroup(GroupDto group) {
		this.group = group;
	}
	
	public List<RoleDto> getRoles() {
		return this.roles;
	}
	
	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}
}
