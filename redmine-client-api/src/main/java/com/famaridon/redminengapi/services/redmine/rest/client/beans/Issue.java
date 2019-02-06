package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Issue extends AbstractRedmineBean {
	
	private Project project;
	private Tracker tracker;
	private Status status;
	private Priority priority;
	private User author;
	@JsonProperty("assigned_to")
	private User assignedTo;
	private Category category;
	@JsonProperty("fixed_version")
	private Version fixedVersion;
	private Issue parent;
	private String subject;
	private String description;
	@JsonProperty("estimated_hours")
	private Integer estimatedHours;
	@JsonProperty("spent_hours")
	private Integer spentHours;
	@JsonProperty("total_spent_hours")
	private Integer totalSpentHours;
	@JsonProperty("start_date")
	private Date startDate;
	@JsonProperty("due_date")
	private Date dueDate;
	@JsonProperty("done_ratio")
	private int doneRatio;
	@JsonProperty("custom_fields")
	private List<CustomField> customFields = new ArrayList<>();
	@JsonProperty("created_on")
	private Date createdOn;
	@JsonProperty("updated_on")
	private Date updatedOn;
	@JsonProperty("closed_on")
	private Date closedOn;
	
	public Project getProject() {
		return this.project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public Tracker getTracker() {
		return this.tracker;
	}
	
	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Priority getPriority() {
		return this.priority;
	}
	
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	public User getAuthor() {
		return this.author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public User getAssignedTo() {
		return this.assignedTo;
	}
	
	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Version getFixedVersion() {
		return this.fixedVersion;
	}
	
	public void setFixedVersion(Version fixedVersion) {
		this.fixedVersion = fixedVersion;
	}
	
	public Issue getParent() {
		return this.parent;
	}
	
	public void setParent(Issue parent) {
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
	
	public List<CustomField> getCustomFields() {
		return this.customFields;
	}

	public Optional<CustomField> findCustomFields(Long fieldId) {
		return this.customFields.stream().filter(cf -> cf.getId()== fieldId).findFirst();
	}
	
	public void setCustomFields(List<CustomField> customFields) {
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
	
	public Integer getEstimatedHours() {
		return this.estimatedHours;
	}
	
	public void setEstimatedHours(Integer estimatedHours) {
		this.estimatedHours = estimatedHours;
	}
	
	public Integer getSpentHours() {
		return this.spentHours;
	}
	
	public void setSpentHours(Integer spentHours) {
		this.spentHours = spentHours;
	}
	
	public Integer getTotalSpentHours() {
		return this.totalSpentHours;
	}
	
	public void setTotalSpentHours(Integer totalSpentHours) {
		this.totalSpentHours = totalSpentHours;
	}
	
	public Date getClosedOn() {
		return this.closedOn;
	}
	
	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Issue)) {
			return false;
		}
		
		Issue issue = (Issue)o;
		
		return new EqualsBuilder().appendSuper(super.equals(o)).append(doneRatio, issue.doneRatio).append(project, issue.project)
			.append(tracker, issue.tracker).append(status, issue.status).append(priority, issue.priority).append(author, issue.author)
			.append(assignedTo, issue.assignedTo).append(category, issue.category).append(fixedVersion, issue.fixedVersion)
			.append(parent, issue.parent).append(subject, issue.subject).append(description, issue.description)
			.append(estimatedHours, issue.estimatedHours).append(spentHours, issue.spentHours).append(totalSpentHours, issue.totalSpentHours)
			.append(startDate, issue.startDate).append(dueDate, issue.dueDate).append(customFields, issue.customFields)
			.append(createdOn, issue.createdOn).append(updatedOn, issue.updatedOn).append(closedOn, issue.closedOn).isEquals();
	}
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(project).append(tracker).append(status).append(priority)
			.append(author).append(assignedTo).append(category).append(fixedVersion).append(parent).append(subject).append(description)
			.append(estimatedHours).append(spentHours).append(totalSpentHours).append(startDate).append(dueDate).append(doneRatio)
			.append(customFields).append(createdOn).append(updatedOn).append(closedOn).toHashCode();
	}
}
