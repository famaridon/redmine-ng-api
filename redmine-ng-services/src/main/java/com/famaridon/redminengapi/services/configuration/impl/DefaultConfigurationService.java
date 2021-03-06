package com.famaridon.redminengapi.services.configuration.impl;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import org.apache.commons.configuration2.CombinedConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.JSONConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.FileBasedBuilderParameters;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.AbsoluteNameLocationStrategy;
import org.apache.commons.configuration2.tree.OverrideCombiner;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Startup
@Singleton
public class DefaultConfigurationService implements ConfigurationService {

  public static final String REDMINE_NG_API_PROFILE = "REDMINE_NG_API_PROFILE";
  private static final Logger LOG = LoggerFactory.getLogger(DefaultConfigurationService.class);
  private final EnvironmentNameChecker environmentNameChecker = new EnvironmentNameChecker();
  private Configuration configuration;

  @PostConstruct
  public void startup() {
    init();
  }

  protected final void init() {
    ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
    LOG.info("Init configuration.");
    try {
      CombinedConfiguration combinedConfiguration = new CombinedConfiguration();
      combinedConfiguration.setNodeCombiner(new OverrideCombiner());

      this.loadProfiledConfiguration(combinedConfiguration);
      this.loadBaseConfiguration(combinedConfiguration, "config.json");
      this.loadDefaultConfiguration(combinedConfiguration);

      this.configuration = combinedConfiguration;

    } catch (ConfigurationException e) {
      throw new IllegalArgumentException("Can't read configuration!", e);
    }

  }

  protected final void loadProfiledConfiguration(CombinedConfiguration combinedConfiguration) throws ConfigurationException {
    Optional<String> profile = this.getEnvironmentProfile();
    if (profile.isPresent()) {
      this.loadBaseConfiguration(combinedConfiguration, "config-" + profile.get() + ".json");
    }
  }

  protected final void loadBaseConfiguration(CombinedConfiguration combinedConfiguration, String fileName) throws ConfigurationException {

    Path configurationPath = Paths.get(fileName);
    File configurationFile = configurationPath.toAbsolutePath().toFile();
    if (!configurationFile.exists()) {
      LOG.warn("Missing configuration file {} .", configurationFile);
      return;
    }
    if (configurationFile.isDirectory()) {
      LOG.warn("Configuration file is a directory {} .", configurationFile);
      return;
    }
    LOG.info("Add configuration file {} .", fileName);
    FileBasedBuilderParameters params = new Parameters()
        .fileBased()
        .setThrowExceptionOnMissing(false)
        .setEncoding("UTF-8")
        .setFileName(configurationFile.getPath())
        .setLocationStrategy(new AbsoluteNameLocationStrategy());
    FileBasedConfigurationBuilder<JSONConfiguration> jsonConfigurationBuilder = new FileBasedConfigurationBuilder<>(
        JSONConfiguration.class);
    combinedConfiguration.addConfiguration(jsonConfigurationBuilder.configure(params).getConfiguration());
  }

  protected final void loadDefaultConfiguration(CombinedConfiguration combinedConfiguration) throws ConfigurationException {
    LOG.info("Add default configuration files.");
    FileBasedBuilderParameters params = new Parameters().fileBased().setThrowExceptionOnMissing(true).setEncoding("UTF-8")
        .setURL(this.getDefaultConfigurationFile());
    FileBasedConfigurationBuilder<JSONConfiguration> jsonConfigurationBuilder = new FileBasedConfigurationBuilder<>(
        JSONConfiguration.class);
    combinedConfiguration.addConfiguration(jsonConfigurationBuilder.configure(params).getConfiguration());
  }

  private Optional<String> getEnvironmentProfile() {
    String profile = System.getenv(REDMINE_NG_API_PROFILE);
    if (StringUtils.isEmpty(profile)) {
      return Optional.empty();
    }
    return environmentNameChecker.validate(profile);
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
