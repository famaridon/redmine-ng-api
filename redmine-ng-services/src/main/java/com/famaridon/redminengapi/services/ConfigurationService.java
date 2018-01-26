package com.famaridon.redminengapi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.infinispan.manager.CacheContainer;

import java.util.List;

public interface ConfigurationService {
	
	String getString(String key);
	
	String getString(String key, String def);
	
	<T> List<T> getList(Class<T> type, String key);
	
	<T> List<T> getList(Class<T> type, String key, List<T> defaultValues);
	
	CacheContainer getCacheContainer();
	
	String buildUrl(String path, Object... parameters);
	
	ObjectMapper getObjectMapper();
}
