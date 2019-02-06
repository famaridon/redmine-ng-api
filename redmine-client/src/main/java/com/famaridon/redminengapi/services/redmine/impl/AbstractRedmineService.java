package com.famaridon.redminengapi.services.redmine.impl;


import com.famaridon.redminengapi.services.redmine.RedmineClientConfiguration;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.HolderResponseHandler;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.PageResponseHandler;
import org.apache.http.client.utils.URIBuilder;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class AbstractRedmineService<B> {

  public static final String X_REDMINE_API_KEY = "X-Redmine-API-Key";

  @Inject
  protected RedmineClientConfiguration redmineClientConfiguration;

  public AbstractRedmineService() {
  }

  protected String getRedmineServer() {

    return this.redmineClientConfiguration.getServerUrl();
  }

  protected URIBuilder getUriBuilder(String path) {
    try {
      return new URIBuilder(this.getRedmineServer() + path);
    } catch (URISyntaxException e) {
      throw new IllegalStateException("Invalid redmine server configuration!" , e);
    }
  }
  protected URI toUri(URIBuilder uriBuilder) {
    try {
      return uriBuilder.build();
    } catch (URISyntaxException e) {
      throw new IllegalStateException(e);
    }
  }
  
  protected PageResponseHandler<B> createPageResponseHandler() {
    return new PageResponseHandler<>(this.getBeanType());
  }
  
  protected HolderResponseHandler<B> createHolderResponseHandler() {
    return new HolderResponseHandler<>(this.getBeanType());
  }
  
  protected abstract Class<B> getBeanType();

}
