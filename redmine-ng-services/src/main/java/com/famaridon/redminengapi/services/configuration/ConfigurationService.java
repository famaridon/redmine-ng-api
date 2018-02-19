package com.famaridon.redminengapi.services.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public interface ConfigurationService {
	
	String getString(String key);
	
	String getString(String key, String def);
	
	<T> List<T> getList(Class<T> type, String key);
	
	<T> List<T> getList(Class<T> type, String key, List<T> defaultValues);
	
	String buildUrl(String path, Object... parameters);
	
	ObjectMapper getObjectMapper();
}