package com.famaridon.redminengapi.services.redmine;

import org.apache.http.client.utils.URIBuilder;

public class Pager implements QueryParamSerializable {
	private final Long offset;
	private final Long limit;
	
	public Pager() {
		this(0L,25L);
	}
	
	public Pager(Long offset, Long limit) {
		this.offset = offset;
		this.limit = limit;
	}
	
	public Long getOffset() {
		return this.offset;
	}
	
	public Long getLimit() {
		return this.limit;
	}

	@Override
	public void serialize(URIBuilder uriBuilder) {
		uriBuilder.addParameter("offset", Long.toString(this.offset));
		uriBuilder.addParameter("limit", Long.toString(this.limit));
	}
}
