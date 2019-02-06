package com.famaridon.redminengapi.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IssueDto extends AbstractDto {

  private ProjectDto project;
  private TrackerDto tracker;
  private StatusDto status;
  private PriorityDto priority;
  private UserDto author;
  @JsonProperty("assigned_to")
  private UserDto assignedTo;
  private CategoryDto category;
  @JsonProperty("fixed_version")
  private VersionDto fixedVersion;
  private IssueDto parent;
  private String subject;
  private String description;
  @JsonProperty("start_date")
  private Date startDate;
  @JsonProperty("due_date")
  private Date dueDate;
  @JsonProperty("done_ratio")
  private int doneRatio;
  @JsonProperty("custom_fields")
  private List<CustomFieldDto> customFields = new ArrayList<>();
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

  public TrackerDto getTracker() {
    return this.tracker;
  }

  public void setTracker(TrackerDto tracker) {
    this.tracker = tracker;
  }

  public StatusDto getStatus() {
    return this.status;
  }

  public void setStatus(StatusDto status) {
    this.status = status;
  }

  public PriorityDto getPriority() {
    return this.priority;
  }

  public void setPriority(PriorityDto priority) {
    this.priority = priority;
  }

  public UserDto getAuthor() {
    return this.author;
  }

  public void setAuthor(UserDto author) {
    this.author = author;
  }

  public UserDto getAssignedTo() {
    return this.assignedTo;
  }

  public void setAssignedTo(UserDto assignedTo) {
    this.assignedTo = assignedTo;
  }

  public CategoryDto getCategory() {
    return this.category;
  }

  public void setCategory(CategoryDto category) {
    this.category = category;
  }

  public VersionDto getFixedVersion() {
    return this.fixedVersion;
  }

  public void setFixedVersion(VersionDto fixedVersion) {
    this.fixedVersion = fixedVersion;
  }

  public IssueDto getParent() {
    return this.parent;
  }

  public void setParent(IssueDto parent) {
    this.parent = parent;
  }

  public String getSubject() {
    return this.subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getStartDate() {
    return this.startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getDueDate() {
    return this.dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public int getDoneRatio() {
    return this.doneRatio;
  }

  public void setDoneRatio(int doneRatio) {
    this.doneRatio = doneRatio;
  }

  public List<CustomFieldDto> getCustomFields() {
    return this.customFields;
  }

  public void setCustomFields(List<CustomFieldDto> customFields) {
    this.customFields = customFields;
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
