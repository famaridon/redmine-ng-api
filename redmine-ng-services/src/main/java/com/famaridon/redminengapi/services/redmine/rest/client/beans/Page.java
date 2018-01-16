package com.famaridon.redminengapi.services.redmine.rest.client.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	
	@JsonProperty("total_count")
	private long totalCount;
	private long offset;
	private long limit;
	
	private List<T> elements = new ArrayList<>();
	
	public long getTotalCount() {
		return this.totalCount;
	}
	
	public void setTotalCount(long total_count) {
		this.totalCount = total_count;
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
}
