package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.fasterxml.jackson.databind.JavaType;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;

import java.io.IOException;

public class PageResponseHandler<T> extends AbstractRedmineJsonResponseHandler<Page<T>> {

  private final JavaType parametricType;

  public PageResponseHandler(Class<T> elementType) {
    super();
    parametricType = this.objectMapper.getTypeFactory().constructParametricType(Page.class, elementType);
  }

  @Override
  protected Page parse(HttpEntity entity, ContentType contentType) throws IOException {
    return this.objectMapper.readValue(entity.getContent(), parametricType);
  }
}
