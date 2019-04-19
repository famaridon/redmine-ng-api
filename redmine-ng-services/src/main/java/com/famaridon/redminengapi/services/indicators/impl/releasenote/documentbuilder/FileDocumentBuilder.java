package com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder;

import com.famaridon.redminengapi.services.indicators.beans.FileType;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Version;
import java.io.File;
import java.util.List;

public class FileDocumentBuilder extends AbstractDocumentBuilder {


  @Override
  public File build(List<Issue> listIssue, Version version, FileType type, String author) {
    return getSaveType(listIssue, version, type, author);
  }

}
