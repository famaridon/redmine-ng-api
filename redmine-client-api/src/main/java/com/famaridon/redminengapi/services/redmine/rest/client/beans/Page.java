package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import com.famaridon.redminengapi.services.redmine.Pager;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Page<T> {
	
	@JsonProperty("total_count")
	private long totalCount;
	private long offset;
	private long limit;
	private List<T> elements = new ArrayList<>();
	
	public long getTotalCount() {
		return this.totalCount;
	}
	
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	public long getOffset() {
		return this.offset;
	}
	
	public void setOffset(long offset) {
		this.offset = offset;
	}
	
	public long getLimit() {
		return this.limit;
	}
	
	public void setLimit(long limit) {
		this.limit = limit;
	}
	
	public List<T> getElements() {
		return this.elements;
	}
	
	public void setElements(List<T> elements) {
		this.elements = elements;
	}
	
	@JsonProperty("projects")
	public void setProjects(List<T> projects) {
		this.elements = projects;
	}
	
	@JsonProperty("queries")
	public void setQueries(List<T> queries) {
		this.elements = queries;
	}
	
	@JsonProperty("memberships")
	public void setMemberships(List<T> memberships) {
		this.elements = memberships;
	}
	
	@JsonProperty("issues")
	public void setIssues(List<T> issues) {
		this.elements = issues;
	}
	
	@JsonProperty("versions")
	public void setVersions(List<T> versions) {
		this.elements = versions;
	}

	public boolean hasNextPage() {
		return this.offset + this.elements.size() < this.totalCount;
	}

	public Optional<Pager> nextPager() {
		if(this.hasNextPage()) {
			Pager nextPager= new Pager(this.offset += this.limit, this.limit);
			return Optional.of(nextPager);
		} else {
			return Optional.empty();
		}
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Page)) {
			return false;
		}
		
		Page<?> page = (Page<?>)o;
		
		return new EqualsBuilder().append(totalCount, page.totalCount).append(offset, page.offset).append(limit, page.limit)
			.append(elements, page.elements).isEquals();
	}
	
	@Override
	public int hashCode()
	{
		return new HashCodeBuilder(17, 37).append(totalCount).append(offset).append(limit).append(elements).toHashCode();
	}
}
