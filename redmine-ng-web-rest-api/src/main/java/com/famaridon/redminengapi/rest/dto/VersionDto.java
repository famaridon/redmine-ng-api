package com.famaridon.redminengapi.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class VersionDto extends AbstractDto {

  private ProjectDto project;
  private String description;
  private String status;
  @JsonProperty("due_date")
  private Date dueDate;
  private String sharing;
  @JsonProperty("created_on")
  private Date createdOn;
  @JsonProperty("updated_on")
  private Date updatedOn;

  public ProjectDto getProject() {
    return this.project;
  }

  public void setProject(ProjectDto project) {
    this.project = project;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getDueDate() {
    return this.dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public String getSharing() {
    return this.sharing;
  }

  public void setSharing(String sharing) {
    this.sharing = sharing;
  }

  public Date getCreatedOn() {
    return this.createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public Date getUpdatedOn() {
    return this.updatedOn;
  }

  public void setUpdatedOn(Date updatedOn) {
    this.updatedOn = updatedOn;
  }
}
