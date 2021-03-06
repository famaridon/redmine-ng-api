package com.famaridon.redminengapi.services.configuration;

import java.util.List;

public interface ConfigurationService {

  String getString(String key);

  String getString(String key, String def);

  Long getLong(String key);

  Long getLong(String key, Long def);

  <T> List<T> getList(Class<T> type, String key);

  <T> List<T> getList(Class<T> type, String key, List<T> defaultValues);

}
