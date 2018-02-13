package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Holder;
import com.fasterxml.jackson.databind.JavaType;
import org.apache.http.HttpEntity;

import java.io.IOException;

public class HolderResponseHandler<T> extends AbstractRedmineResponseHandler<T> {
	
	private final JavaType parametricType;
	
	public HolderResponseHandler(ConfigurationService configurationService, Class<T> elementType) {
		super(configurationService);
		parametricType = this.configurationService.getObjectMapper().getTypeFactory().constructParametricType(Holder.class, elementType);
	}
	
	@Override
	protected T parse(HttpEntity entity) throws IOException {
		Holder<T> holder = this.configurationService.getObjectMapper().readValue(entity.getContent(), this.parametricType);
		return holder.getElement();
	}
}
