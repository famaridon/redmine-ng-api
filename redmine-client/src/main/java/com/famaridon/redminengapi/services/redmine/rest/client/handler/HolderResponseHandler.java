package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Holder;
import com.fasterxml.jackson.databind.JavaType;
import java.io.IOException;
import org.apache.http.HttpEntity;

public class HolderResponseHandler<T> extends AbstractRedmineResponseHandler<T> {
	
	private final JavaType parametricType;
	
	public HolderResponseHandler(Class<T> elementType) {
		super();
		parametricType = this.objectMapper.getTypeFactory().constructParametricType(Holder.class, elementType);
	}
	
	@Override
	protected T parse(HttpEntity entity) throws IOException {
		Holder<T> holder = this.objectMapper.readValue(entity.getContent(), this.parametricType);
		return holder.getElement();
	}
}
