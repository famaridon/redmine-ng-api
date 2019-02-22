package com.famaridon.redminengapi.services.redmine.filter;

public class LongFilter extends AbstractFilter<Long> {

  public LongFilter(String field, Long value) {
    super(field, value);
  }

  @Override
  public String getFilter() {
    return Long.toString(this.value);
  }
}
