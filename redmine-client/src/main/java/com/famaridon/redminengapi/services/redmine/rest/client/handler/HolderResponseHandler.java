package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Holder;
import com.fasterxml.jackson.databind.JavaType;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;

import java.io.IOException;

public class HolderResponseHandler<T> extends AbstractRedmineJsonResponseHandler<T> {

  private final JavaType parametricType;

  public HolderResponseHandler(Class<T> elementType) {
    super();
    parametricType = this.objectMapper.getTypeFactory().constructParametricType(Holder.class, elementType);
  }

  @Override
  protected T parse(HttpEntity entity, ContentType contentType) throws IOException {
    Holder<T> holder = this.objectMapper.readValue(entity.getContent(), this.parametricType);
    return holder.getElement();
  }
}
