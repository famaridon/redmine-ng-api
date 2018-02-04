package com.famaridon.redminengapi.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class JacksonMapperContextResolver implements ContextResolver<ObjectMapper> {
	
	private final ObjectMapper mapper;
	
	public JacksonMapperContextResolver() {
		this.mapper = new ObjectMapper();
		this.mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		this.mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}
	
	@Override
	public ObjectMapper getContext(Class<?> type) {
		return this.mapper;
	}
	
}
