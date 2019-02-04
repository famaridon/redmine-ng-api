package com.famaridon.redminengapi.services.configuration.impl;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.configuration2.CombinedConfiguration;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.combined.CombinedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.FileBasedBuilderParameters;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.BasePathLocationStrategy;
import org.apache.commons.configuration2.io.FileLocationStrategy;
import org.apache.commons.configuration2.tree.NodeCombiner;
import org.apache.commons.configuration2.tree.OverrideCombiner;
import org.apache.commons.lang3.StringUtils;
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
  public static final String REDMINE_NG_API_PROFILE = "REDMINE_NG_API_PROFILE";

  private Configuration configuration;

  @PostConstruct
  public void startup() {
    init();
  }

  protected final void init() {
    ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
    LOG.info("Init configuration.");
    try {

      String profile = System.getenv(REDMINE_NG_API_PROFILE);

      CombinedConfiguration combinedConfiguration = new CombinedConfiguration();
      combinedConfiguration.setNodeCombiner(new OverrideCombiner());

      if (StringUtils.isNotBlank(profile)) {
        combinedConfiguration.addConfiguration(this.getConfiguration(profile));
      }
      combinedConfiguration.addConfiguration(this.getConfiguration(null));
      combinedConfiguration.addConfiguration(this.getDefaultConfiguration());


      this.configuration = combinedConfiguration;
    } catch (ConfigurationException e) {
      throw new IllegalArgumentException("Can't read configuration!", e);
    }

  }

  protected final Configuration getConfiguration(String profile) throws ConfigurationException {
    Path configurationFile;
    if (StringUtils.isEmpty(profile)) {
      configurationFile = Paths.get(".","config.json");
    } else {
      // Restrict the profile to letters and digits only
      if (!profile.matches("[a-zA-Z0-9]++")) {
        throw new IllegalArgumentException("profile must only contane letter and digits");
      }
      configurationFile = Paths.get(".","config-" + profile + ".json");
    }
    LOG.info("Add optional {} configuration files.", configurationFile);
    FileBasedBuilderParameters params = new Parameters()
        .fileBased()
        .setThrowExceptionOnMissing(false)
        .setEncoding("UTF-8")
        .setFile(configurationFile.toAbsolutePath().toFile());
    FileBasedConfigurationBuilder<JSONConfiguration> jsonConfigurationBuilder = new FileBasedConfigurationBuilder<>(
        JSONConfiguration.class);
    return jsonConfigurationBuilder.configure(params).getConfiguration();
  }

  protected final Configuration getDefaultConfiguration() throws ConfigurationException {
    LOG.info("Add default configuration files.");
    FileBasedBuilderParameters params = new Parameters()
        .fileBased()
        .setThrowExceptionOnMissing(true)
        .setEncoding("UTF-8")
        .setURL(this.getDefaultConfigurationFile());
    FileBasedConfigurationBuilder<JSONConfiguration> jsonConfigurationBuilder = new FileBasedConfigurationBuilder<>(
        JSONConfiguration.class);
    return jsonConfigurationBuilder.configure(params).getConfiguration();
  }

  protected final URL getDefaultConfigurationFile() {
    return DefaultConfigurationService.class.getResource("/config.json");
  }

  @Override
  public String getString(String key) {
    return this.configuration.getString(key);
  }

  @Override
  public String getString(String key, String def) {
    return this.configuration.getString(key, def);
  }

  @Override
  public Long getLong(String key) {
    return this.configuration.getLong(key);
  }

  @Override
  public Long getLong(String key, Long def) {
    return this.configuration.getLong(key, def);
  }

  @Override
  public <T> List<T> getList(Class<T> type, String key) {
    return this.configuration.getList(type, key);
  }

  @Override
  public <T> List<T> getList(Class<T> type, String key, List<T> defaultValues) {
    return this.configuration.getList(type, key, defaultValues);
  }


}
