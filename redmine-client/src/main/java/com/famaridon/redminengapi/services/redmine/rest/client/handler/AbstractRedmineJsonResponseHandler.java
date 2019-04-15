package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import com.famaridon.redminengapi.services.redmine.rest.client.module.RedmineClientModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;

import java.io.IOException;

public abstract class AbstractRedmineJsonResponseHandler<T> extends AbstractRedmineResponseHandler<T> {

  protected final ObjectMapper objectMapper;

  protected AbstractRedmineJsonResponseHandler() {
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new RedmineClientModule());
  }
  
  protected ContentType getExpectedMimeType() {
    return ContentType.APPLICATION_JSON;
  }

  protected abstract T parse(HttpEntity entity, ContentType contentType) throws IOException;
}
