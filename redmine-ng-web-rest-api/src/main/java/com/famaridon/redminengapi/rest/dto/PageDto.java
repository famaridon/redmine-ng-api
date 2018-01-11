package com.famaridon.redminengapi.rest.dto;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class PageDto<T> {
	
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
}