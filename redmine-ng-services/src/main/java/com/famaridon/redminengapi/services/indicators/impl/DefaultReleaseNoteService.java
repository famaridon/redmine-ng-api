package com.famaridon.redminengapi.services.indicators.impl;


import com.famaridon.redminengapi.services.indicators.ReleaseNoteService;
import com.famaridon.redminengapi.services.indicators.beans.FileType;
import com.famaridon.redminengapi.services.indicators.beans.Header;
import com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder.DocumentBuilderFactory;
import com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder.IDocumentBuilder;
import com.famaridon.redminengapi.services.redmine.Filter;
import com.famaridon.redminengapi.services.redmine.FilterFactory;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Release note service
 */
public class DefaultReleaseNoteService implements ReleaseNoteService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultReleaseNoteService.class);
  private static final String API_KEY = "005556de9c58b855dd32042afb8955858eb02c01";
  private static final Long PAGER_OFFSET = 0L;
  private static final Long PAGER_LIMIT = 50L;
  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
  private static final SimpleDateFormat DATE_FORMAT_LONG = new SimpleDateFormat("yyyMMddHHmm");

  @Inject
  private IssueService issueService;
  @Inject
  private FilterFactory filterFactory;

  @Override
  public Response generateReleaseNote(FileType type, String version, String product, Long idV) {
    try {
      Pager page = new Pager(PAGER_OFFSET, PAGER_LIMIT);
      List<Filter> filter = getFilter(idV);
      Page<Issue> listIssue = issueService.findAllByFilters(API_KEY, filter, page);
      Header header = getHeader(version, product);
      String name = header.getName();
      DocumentBuilderFactory factory = new DocumentBuilderFactory();
      IDocumentBuilder documentBuilder = factory.getDocumentBuilder(type);
      File file = documentBuilder.getFile(listIssue,name,header);

      try {
        file.createTempFile(name,type.getExtension());
      } catch (IOException e) {
        LOGGER.warn("createTempFile problem", e);
      }
      return Response.ok(file,type.getMimeType()).header("Content-Disposition", "attachment; filename=\""+name+"."+type.getExtension()+"\"").build();
    } catch (IOException e) {
      LOGGER.warn("No issue in the list", e);
      return Response.noContent().build();
    }
  }

  private Header getHeader(String version, String product){
    Date actual = new Date();
    String date = DATE_FORMAT.format(actual);
    String name = "RLN-" + DATE_FORMAT_LONG.format(actual);
    String author = "Arthur Pelofi";
    return new Header(name, author, date, product, version);
  }


  private List<Filter> getFilter(Long idV){
    List<Filter> filter = new ArrayList<>();
    filter.add(this.filterFactory.createStatusFilter(5L));
    filter.add(this.filterFactory.createVersionFilter(idV));
    return filter;
  }
}

