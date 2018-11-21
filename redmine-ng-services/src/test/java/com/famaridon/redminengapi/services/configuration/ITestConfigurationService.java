package com.famaridon.redminengapi.services.configuration;

import com.famaridon.redminengapi.services.redmine.rest.client.module.RedmineClientModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Startup
@Singleton
public class ITestConfigurationService implements ConfigurationService {

    private Map<String, String> configuration;
    private ObjectMapper objectMapper;

    @PostConstruct
    protected void startup() {
        init();
    }

    protected final void init() {
        this.configuration = new HashMap<>();
        this.configuration.put("redmine.server.url", System.getenv("TEST_REDMINE_SERVER"));
        System.out.println("TEST_REDMINE_SERVER : " + System.getenv("TEST_REDMINE_SERVER"));

        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new RedmineClientModule());    }

    @Override
    public String getString(String key) {
        return this.configuration.get(key);
    }

    @Override
    public String getString(String key, String def) {
        return this.configuration.getOrDefault(key, def);
    }

    @Override
    public <T> List<T> getList(Class<T> type, String key) {
        throw new NotImplementedException("Mock not implemented");
    }

    @Override
    public <T> List<T> getList(Class<T> type, String key, List<T> defaultValues) {
        throw new NotImplementedException("Mock not implemented");
    }

    protected String getRedmineServer() {
        return this.configuration.get("redmine.server.url");
    }

    public String buildUrl(String path, Object... parameters) {
        return this.getRedmineServer() + String.format(path, parameters);
    }

    @Produces
    @Override
    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }
}
