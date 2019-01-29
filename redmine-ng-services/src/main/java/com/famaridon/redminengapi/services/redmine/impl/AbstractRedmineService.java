package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.FilterFactory;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;

public abstract class AbstractRedmineService<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRedmineService.class);
  public static final String X_REDMINE_API_KEY = "X-Redmine-API-Key";

  @EJB
  protected ConfigurationService configurationService;
  protected final FilterFactory filterFactory = new FilterFactory();

  public AbstractRedmineService() {
  }

  protected String getRedmineServer() {

    return this.configurationService.getString("redmine.server.url");
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

}
