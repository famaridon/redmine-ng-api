package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User extends AbstractRedmineBean {
	private String login;
	private String gravatar;
	private String firstname;
	private String lastname;
	private String mail;
	@JsonProperty("created_on")
	private Date createdOn;
	@JsonProperty("last_login_on")
	private Date lastLoginOn;
	@JsonProperty("api_key")
	private String apiKey;
	@JsonProperty("custom_fields")
	private List<CustomField> customFields = new ArrayList<>();
	
	public String getLogin() {
		return this.login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getGravatar() {
		return this.gravatar;
	}
	
	public void setGravatar(String gravatar) {
		this.gravatar = gravatar;
	}
	
	public String getFirstname() {
		return this.firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return this.lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getMail() {
		return this.mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public Date getCreatedOn() {
		return this.createdOn;
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public Date getLastLoginOn() {
		return this.lastLoginOn;
	}
	
	public void setLastLoginOn(Date lastLoginOn) {
		this.lastLoginOn = lastLoginOn;
	}
	
	public String getApiKey() {
		return this.apiKey;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public List<CustomField> getCustomFields() {
		return this.customFields;
	}
	
	public void setCustomFields(List<CustomField> customFields) {
		this.customFields = customFields;
	}
}
