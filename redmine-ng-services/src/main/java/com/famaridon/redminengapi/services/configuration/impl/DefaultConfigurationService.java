package com.famaridon.redminengapi.services.configuration.impl;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.net.URL;
import java.util.List;

@Startup
@Singleton
public class DefaultConfigurationService implements ConfigurationService {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultConfigurationService.class);
	
	private Configuration configuration;
	
	@PostConstruct
	public void startup() {
		init();
	}
	
	protected final void init() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		LOG.info("Init configuration.");
		Configurations configs = new Configurations();
		try {
			LOG.info("Read configuration files.");
			this.configuration = configs.fileBased(JSONConfiguration.class,getConfigurationFile());
		} catch (ConfigurationException e) {
			throw new IllegalArgumentException("Can't read configuration!");
		}

	}
	
	protected URL getConfigurationFile() {
		return DefaultConfigurationService.class.getResource("/config.json");
	}
	
	@Override
	public String getString(String key) {
		return this.configuration.getString(key);
	}
	
	@Override
	public <T> List<T> getList(Class<T> type, String key) {
		return this.configuration.getList(type, key);
	}
	
	@Override
	public <T> List<T> getList(Class<T> type, String key, List<T> defaultValues) {
		return this.configuration.getList(type, key, defaultValues);
	}
	
	@Override
	public String getString(String key, String def) {
		return this.configuration.getString(key, def);
	}
	
	
	
	protected String getRedmineServer() {
		return this.configuration.getString("redmine.server.url");
	}
	
	public String buildUrl(String path, Object... parameters) {
		return this.getRedmineServer() + String.format(path, parameters);
	}
	
}
