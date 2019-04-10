package com.famaridon.redminengapi.services.indicators.impl;


import com.famaridon.redminengapi.services.indicators.ReleaseNoteService;
import com.famaridon.redminengapi.services.indicators.beans.FileType;
import com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder.DocumentBuilder;
import com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder.DocumentBuilderFactory;
import com.famaridon.redminengapi.services.redmine.Filter;
import com.famaridon.redminengapi.services.redmine.FilterFactory;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.StatusType;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Version;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Release note service
 */
public class DefaultReleaseNoteService implements ReleaseNoteService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultReleaseNoteService.class);
  private static final Long PAGER_OFFSET = 0L;
  private static final Long PAGER_LIMIT = 50L;

  @Inject
  private IssueService issueService;
  @Inject
  private FilterFactory filterFactory;

  @Override
  public File generateReleaseNote(FileType type, String apiKey, Version version) {
    try {
      Pager page = new Pager(PAGER_OFFSET, PAGER_LIMIT);
      List<Filter> filter = buildReleaseNoteFilters(version);
      Page<Issue> listIssue = issueService.findAllByFilters(apiKey, filter, page);
      DocumentBuilderFactory factory = new DocumentBuilderFactory();
      DocumentBuilder documentBuilder = factory.getDocumentBuilder(type);
      return documentBuilder.build(listIssue, version, type);
    } catch (IOException e) {
      LOGGER.error("No issue in the list", e);
      throw new IllegalStateException(e);
    }
  }

  private List<Filter> buildReleaseNoteFilters(Version version){
    List<Filter> filter = new ArrayList<>();
    filter.add(this.filterFactory.createStatusFilter(StatusType.CLOSED));
    filter.add(this.filterFactory.createVersionFilter(version.getId()));
    return filter;
  }
}

