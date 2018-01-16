package com.famaridon.redminengapi.services;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface ConfigurationService {
	
	String getString(String s);
	
	String getString(String s, String s1);
	
	String buildUrl(String path, Object... parameters);
	
	ObjectMapper getObjectMapper();
}
