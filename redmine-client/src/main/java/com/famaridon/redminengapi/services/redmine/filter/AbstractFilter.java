package com.famaridon.redminengapi.services.redmine.filter;

import com.famaridon.redminengapi.services.redmine.Filter;
import org.apache.http.client.utils.URIBuilder;

public abstract class AbstractFilter<T> implements Filter {

  protected final String field;
  protected final T value;

  protected AbstractFilter(String field, T value) {
    this.field = field;
    this.value = value;
  }

  @Override
  public void serialize(URIBuilder uriBuilder) {
    uriBuilder.addParameter(this.field, this.getFilter());
  }

  protected abstract String getFilter();

}
