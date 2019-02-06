package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.fasterxml.jackson.databind.JavaType;
import java.io.IOException;
import org.apache.http.HttpEntity;

public class PageResponseHandler<T> extends AbstractRedmineResponseHandler<Page<T>> {
	
	private final JavaType parametricType;
	
	public PageResponseHandler(Class<T> elementType) {
		super();
		parametricType = this.objectMapper.getTypeFactory().constructParametricType(Page.class, elementType);
	}
	
	@Override
	protected Page parse(HttpEntity entity) throws IOException {
		return this.objectMapper.readValue(entity.getContent(),parametricType);
	}
}
