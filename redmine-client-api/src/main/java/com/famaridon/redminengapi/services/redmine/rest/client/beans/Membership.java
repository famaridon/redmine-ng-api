package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import java.util.ArrayList;
import java.util.List;

public class Membership extends AbstractRedmineBean {

	private Project project;
	private User user;
	private Group group;
	private List<Role> roles = new ArrayList<>();
	
	public Project getProject() {
		return this.project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Group getGroup() {
		return this.group;
	}
	
	public void setGroup(Group group) {
		this.group = group;
	}
	
	public List<Role> getRoles() {
		return this.roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
