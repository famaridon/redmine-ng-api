package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.rest.dto.Paginable;
import com.famaridon.redminengapi.services.ConfigurationService;
import org.apache.http.client.fluent.Request;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;

public abstract class AbstractRedmineService<T> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRedmineService.class);
	
	protected final TypeReference<T> typeReference;
	protected final TypeReference<Paginable<T>> paginableTypeReference;
	
	@EJB
	protected ConfigurationService configurationService;
	
	public AbstractRedmineService() {
		this.typeReference = new TypeReference<T>() {};
		this.paginableTypeReference = new TypeReference<Paginable<T>>() {};
	}
	
	protected Request initGetRequest(String path, String key) {
		String url = this.configurationService.getRedmineServer() + path;
		LOGGER.info("proxify GET request {}", url);
		return Request.Get(url).addHeader("X-Redmine-API-Key", key).connectTimeout(1000).socketTimeout(1000);
	}
	
}
