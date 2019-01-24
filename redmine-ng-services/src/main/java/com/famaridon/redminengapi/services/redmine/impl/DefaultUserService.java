package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.cache.annotation.CacheKey;
import com.famaridon.redminengapi.services.cache.annotation.CacheName;
import com.famaridon.redminengapi.services.cache.annotation.CachePut;
import com.famaridon.redminengapi.services.redmine.UserService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.HolderResponseHandler;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.HtmlResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Stateless
public class DefaultUserService extends AbstractRedmineService<User> implements UserService {

  private static final Logger LOG = LoggerFactory.getLogger(DefaultUserService.class);

  @Override
  @CachePut
  @CacheName("userByApiKey")
  public User findCurrent(@CacheKey String apiKey) throws IOException {
    URIBuilder uriBuilder = this.getUriBuilder("/users/current.json");
    User r = Request.Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiKey)
        .execute()
        .handleResponse(new HolderResponseHandler<>(User.class));
    r = this.loadGravatar(apiKey, r);
    r.setApiKey(apiKey);
    return r;
  }

  @Override
  @CachePut
  @CacheName("userById")
  public User findById(String apiKey, @CacheKey long id) throws IOException {
    URIBuilder uriBuilder = this.getUriBuilder("/users/" + id + ".json");
    User r = Request.Get(this.toUri(uriBuilder))
        .addHeader(X_REDMINE_API_KEY, apiKey)
        .execute()
        .handleResponse(new HolderResponseHandler<>(User.class));
    r = this.loadGravatar(apiKey, r);
    return r;
  }

  public User loadGravatar(String apiKey, User user) {
    try {
      URIBuilder uriBuilder = this.getUriBuilder("/users/" + user.getId() + ".html");
      Document d = Request.Get(this.toUri(uriBuilder))
          .addHeader(X_REDMINE_API_KEY, apiKey)
          .execute()
          .handleResponse(new HtmlResponseHandler(this.getRedmineServer()));
      Element img = d.selectFirst("#main #content img.gravatar");
      user.setGravatar(img.attr("src"));
    } catch (IOException e) {
      throw new IllegalStateException("Can't join Redmine server", e);
    }
    return user;
  }

  @Override
  public List<String> findRoles(String login) {
    return this.configurationService
        .getList(String.class, "security.users." + login, Collections.emptyList());
  }
}
