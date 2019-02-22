package com.famaridon.redminengapi.services.redmine.rest.client.module;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.CustomField;
import com.famaridon.redminengapi.services.redmine.rest.client.deserializer.CustomFieldDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class RedmineClientModule extends SimpleModule {

  public RedmineClientModule() {
    super();
    this.addDeserializer(CustomField.class, new CustomFieldDeserializer());
  }

  @Override
  public String getModuleName() {
    return "redmine-client";
  }

}
