package com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder;

import com.aspose.words.Document;
import com.aspose.words.SaveOptions;
import com.famaridon.redminengapi.services.indicators.beans.FileType;
import com.famaridon.redminengapi.services.indicators.beans.Header;
import com.famaridon.redminengapi.services.indicators.impl.releasenote.IssueList;
import com.famaridon.redminengapi.services.indicators.impl.releasenote.IssueTemp;
import com.famaridon.redminengapi.services.indicators.impl.releasenote.TableDataSource;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Version;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractDocumentBuilder implements DocumentBuilder {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDocumentBuilder.class);
  private static final String TEMPLATE_DOC = "template.doc";
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
  private static final DateFormat DATE_FORMAT_LONG = new SimpleDateFormat("yyyMMddHHmm");
  private Header header;
  private String date;

  public Header getHeader() {
    return header;
  }

  private Document doMailMerge(List<Issue> listIssue, Header header){
    Document document = getDocumentFromTemplate();
    mergeHeader(header, document);
    TableDataSource tableIssue = getTableDataSource(listIssue);
    mergeWithRegions(document, tableIssue);
    return document;
  }

  private void mergeWithRegions(Document document, TableDataSource tableIssue) {
    try {
      document.getMailMerge().executeWithRegions(tableIssue);
    } catch (Exception e) {
      LOGGER.error("executeWithRegions problem", e);
      throw new IllegalStateException(e);
    }
  }

  private void mergeHeader(Header header, Document document) {
    try {
      document.getMailMerge().execute(new String[]{Header.FIELD_REF, Header.FIELD_AUTHOR, Header.FIELD_DATE, Header.FIELD_PRODUCT, Header.FIELD_VERSION},
          new Object[]{header.getName(), header.getAuthor(), header.getDate(), header.getProduct(), header.getVersion()});
    } catch (Exception e) {
      LOGGER.error("Header execute MailMerge problem", e);
      throw new IllegalStateException(e);
    }
  }

  private TableDataSource getTableDataSource(List<Issue> listIssue) {
    IssueList issues = new IssueList();
    for (Issue issue : listIssue) {
      issues.add(new IssueTemp(issue.getSubject(),issue.getId()));
    }
    return new TableDataSource(issues);
  }

  private Document getDocumentFromTemplate() {
    Document document;
    try {
      document = new Document(TEMPLATE_DOC);
      document.getMailMerge().setTrimWhitespaces(false);
    } catch (Exception e) {
      LOGGER.error("No template found", e);
      throw new IllegalStateException(e);
    }
    return document;
  }

  private File saveAsposeDocument(String name, Document doc, FileType type) throws Exception {
    String filename = name + "." + type.getExtension();
    File file = new File(filename);

    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
      doc.save(fileOutputStream, SaveOptions.createSaveOptions(filename));
    }

    return file;
  }
  String setDate(Date d) {
    date = DATE_FORMAT_LONG.format(d);
    return date;
  }

  private Header createHeader(Version version){
    Date actual = new Date();
    String date = DATE_FORMAT.format(actual);
    String name = "RLN-" + setDate(actual);
    String author = "Arthur Pelofi";
    return new Header(name, author, date, version);
  }
  File getSaveType(List<Issue> listIssue, Version version, FileType type) {
    this.header = createHeader(version);
    Document document = doMailMerge(listIssue, this.header);
    try {
      return saveAsposeDocument(this.header.getName(), document, type);
    } catch (FileNotFoundException e) {
      LOGGER.error("File not found", e);
      throw new IllegalStateException(e);
    } catch (Exception e) {
      LOGGER.error("Cannot save document", e);
      throw new IllegalStateException(e);
    }
  }

}
