package com.famaridon.redminengapi.services.indicators.impl.issue;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;

public interface IssueOperator<T> {

  T operation(T previous, Issue issue);

}
