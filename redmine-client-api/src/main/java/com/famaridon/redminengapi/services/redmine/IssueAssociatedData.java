package com.famaridon.redminengapi.services.redmine;

public enum IssueAssociatedData {
	
//	CHILDREN("children"),
//	ATTACHMENTS("attachments"),
//	RELATIONS("relations"),
//	CHANGESETS("changesets"),
	JOURNALS("journals");
//	WATCHERS("watchers");
	
	public final String queryParam;
	
	IssueAssociatedData(String queryParam) {
		this.queryParam = queryParam;
	}
}
