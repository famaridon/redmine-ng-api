package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import com.famaridon.redminengapi.services.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.fasterxml.jackson.databind.JavaType;
import org.apache.http.HttpEntity;

import java.io.IOException;

public class PageResponseHandler<T> extends AbstractRedmineResponseHandler<Page<T>> {
	
	private final JavaType parametricType;
	
	public PageResponseHandler(ConfigurationService configurationService, Class<T> elementType) {
		super(configurationService);
		parametricType = this.configurationService.getObjectMapper().getTypeFactory().constructParametricType(Page.class, elementType);
	}
	
	@Override
	protected Page parse(HttpEntity entity) throws IOException {
		Page<T> page = this.configurationService.getObjectMapper().readValue(entity.getContent(),parametricType);
		return page;
	}
}
