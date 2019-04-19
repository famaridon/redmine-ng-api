package com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder;

import com.famaridon.redminengapi.services.indicators.beans.FileType;
import com.famaridon.redminengapi.services.indicators.beans.Header;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Version;
import java.io.File;
import java.util.List;

public interface DocumentBuilder {
  File build(List<Issue> listIssue, Version version, FileType type, String author);
  Header getHeader();
}
