package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.realtime.beans.UserStatus;
import com.famaridon.redminengapi.services.redmine.UserService;
import com.famaridon.redminengapi.services.cache.CacheKey;
import com.famaridon.redminengapi.services.cache.CacheName;
import com.famaridon.redminengapi.services.cache.CachePut;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.HolderResponseHandler;
import com.famaridon.redminengapi.services.redmine.rest.client.handler.HtmlResponseHandler;
import org.apache.http.client.fluent.Request;
import org.infinispan.Cache;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@Stateless
public class DefaultUserService extends AbstractRedmineService<User> implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultUserService.class);
	
	@Inject
	private Cache<UserStatus, Set<Long>> usersByUsersStatusCache;
	
	@Override
	@CachePut
	@CacheName("userByApiKey")
	public User findCurrent(@CacheKey String apiKey) throws IOException {
		User r = Request.Get(this.configurationService.buildUrl("/users/current.json"))
			.addHeader(X_REDMINE_API_KEY, apiKey)
			.execute()
			.handleResponse(new HolderResponseHandler<>(this.configurationService, User.class));
		r = this.loadGravatar(apiKey, r);
		return r;
	}
	
	@Override
	@CachePut
	@CacheName("userById")
	public User findById(String apiKey, @CacheKey long id) throws IOException {
		User r = Request.Get(this.configurationService.buildUrl("/users/%s.json", id))
			.addHeader(X_REDMINE_API_KEY, apiKey)
			.execute()
			.handleResponse(new HolderResponseHandler<>(this.configurationService, User.class));
		r = this.loadGravatar(apiKey, r);
		return r;
	}
	
	public User loadGravatar(String apiKey, User user) {
		try {
			Document d = Request.Get(this.configurationService.buildUrl("/users/%s.html",user.getId()))
				.addHeader(X_REDMINE_API_KEY, apiKey)
				.execute()
				.handleResponse(new HtmlResponseHandler(this.configurationService));
			Element img = d.selectFirst("#main #content img.gravatar");
			user.setGravatar(img.attr("src"));
		} catch (IOException e) {
			throw new IllegalStateException("Can't join Redmine server",e);
		}
		return user;
	}
	
	@Override
	public Set<Long> getUserByUsersStatus(UserStatus userStatus)
	{
		Set<Long> connectedUsers = this.usersByUsersStatusCache.get(userStatus);
		if(connectedUsers == null){
			connectedUsers = Collections.emptySet();
		}
		return connectedUsers;
	}

}
