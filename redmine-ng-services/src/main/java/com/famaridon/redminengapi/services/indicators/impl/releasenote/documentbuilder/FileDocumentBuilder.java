package com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder;

import com.famaridon.redminengapi.services.indicators.beans.FileType;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Version;
import java.io.File;

public class FileDocumentBuilder extends AbstractDocumentBuilder {


  @Override
  public File build(Page<Issue> listIssue, Version version, FileType type) {
    return getSaveType(listIssue, version, type);
  }

}
