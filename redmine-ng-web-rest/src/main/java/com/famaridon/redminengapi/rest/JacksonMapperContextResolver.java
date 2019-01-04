package com.famaridon.redminengapi.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class JacksonMapperContextResolver implements ContextResolver<ObjectMapper> {
	
	private final ObjectMapper mapper;
	
	public JacksonMapperContextResolver() {
		this.mapper = new ObjectMapper();
		this.mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		this.mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		
		this.mapper.registerModule(new Jdk8Module());
		this.mapper.registerModule(new JavaTimeModule());
	}
	
	@Override
	public ObjectMapper getContext(Class<?> type) {
		return this.mapper;
	}
	
}
