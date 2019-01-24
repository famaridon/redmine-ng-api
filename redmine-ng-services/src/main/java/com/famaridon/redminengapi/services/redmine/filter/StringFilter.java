package com.famaridon.redminengapi.services.redmine.filter;

public class StringFilter extends AbstractFilter<String> {

  public StringFilter(String field, String value) {
    super(field, value);
  }

  @Override
  public String getFilter() {
    return this.value;
  }
}
