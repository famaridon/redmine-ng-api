package com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder;

import com.famaridon.redminengapi.services.indicators.beans.Header;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.io.File;

public class ZipDocumentBuilder extends AbstractDocumentBuilder {

  @Override
  public File getFile(Page<Issue> listIssue, String name, Header header) {
    return null;
  }
}
