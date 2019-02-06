package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Membership)) {
			return false;
		}
		
		Membership that = (Membership)o;
		
		return new EqualsBuilder().appendSuper(super.equals(o)).append(project, that.project).append(user, that.user).append(group, that.group)
			.append(roles, that.roles).isEquals();
	}
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(project).append(user).append(group).append(roles).toHashCode();
	}
}
