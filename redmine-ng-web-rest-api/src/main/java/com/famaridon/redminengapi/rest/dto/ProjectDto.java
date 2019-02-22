package com.famaridon.redminengapi.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectDto extends AbstractDto {

  private String identifier;
  private String description;
  private long status;
  @JsonProperty("created_on")
  private Date createdOn;
  @JsonProperty("updated_on")
  private Date updatedOn;
  private ProjectDto parent;
  private List<TrackerDto> trackers = new ArrayList<>();
  @JsonProperty("issue_categories")
  private List<CategoryDto> issueCategories = new ArrayList<>();

  public String getIdentifier() {
    return this.identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public long getStatus() {
    return this.status;
  }

  public void setStatus(long status) {
    this.status = status;
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

  public ProjectDto getParent() {
    return this.parent;
  }

  public void setParent(ProjectDto parent) {
    this.parent = parent;
  }

  public List<TrackerDto> getTrackers() {
    return this.trackers;
  }

  public void setTrackers(List<TrackerDto> trackers) {
    this.trackers = trackers;
  }

  public List<CategoryDto> getIssueCategories() {
    return this.issueCategories;
  }

  public void setIssueCategories(List<CategoryDto> issueCategories) {
    this.issueCategories = issueCategories;
  }
}
