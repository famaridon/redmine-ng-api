package com.famaridon.redminengapi.services.indicators.impl;


import com.famaridon.redminengapi.services.indicators.ReleaseNoteService;
import com.famaridon.redminengapi.services.indicators.beans.FileType;
import com.famaridon.redminengapi.services.indicators.impl.releasenote.PageWalker;
import com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder.DocumentBuilder;
import com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder.DocumentBuilderFactory;
import com.famaridon.redminengapi.services.redmine.Filter;
import com.famaridon.redminengapi.services.redmine.FilterFactory;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.StatusType;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Version;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Release note service
 */
public class DefaultReleaseNoteService implements ReleaseNoteService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultReleaseNoteService.class);

  @Inject
  private IssueService issueService;
  @Inject
  private FilterFactory filterFactory;

  /**
   * TODO : author
   * Faut-il utiliser le Sécurity Context au niveau de l'AbstractDocumentBuilder?
   *   -> Permet de ne pas transmettre le string author via :
   *         -DefaultReleaseNoteService, DocumentBuilder, AbstractDocumentBuilder et Header
   */
  @Override
  public File generateReleaseNote(FileType type, String apiKey, Version version, SecurityContext securityContext) {
    String author =securityContext.getUserPrincipal().getName();
    List<Filter> filter = buildReleaseNoteFilters(version);
    PageWalker walker = new PageWalker();
    Future<List<Issue>> futureIssueList = walker.walk(pager -> this.issueService.findAllByFilters(apiKey,filter,pager));
    DocumentBuilderFactory factory = new DocumentBuilderFactory();
    DocumentBuilder documentBuilder = factory.getDocumentBuilder(type);
    try {
      List<Issue> issueList = futureIssueList.get();
      return documentBuilder.build(issueList, version, type, author);
    } catch (InterruptedException e) {
      LOGGER.error("Execution thread interrupted", e);
      throw new IllegalStateException(e);
    } catch (ExecutionException e) {
      LOGGER.error("Execution thread fail", e);
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

