package com.famaridon.redminengapi.services.redmine;

public class Pager {
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
	public String toString() {
		return "offset="+this.offset+"&limit="+ this.limit;
	}
}
