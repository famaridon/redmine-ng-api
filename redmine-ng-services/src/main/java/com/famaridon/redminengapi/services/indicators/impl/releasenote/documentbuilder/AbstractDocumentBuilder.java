package com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder;

import com.aspose.words.Document;
import com.aspose.words.SaveOptions;
import com.famaridon.redminengapi.services.indicators.beans.FileType;
import com.famaridon.redminengapi.services.indicators.beans.Header;
import com.famaridon.redminengapi.services.indicators.impl.releasenote.IssueList;
import com.famaridon.redminengapi.services.indicators.impl.releasenote.IssueTemp;
import com.famaridon.redminengapi.services.indicators.impl.releasenote.TableDataSource;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractDocumentBuilder implements IDocumentBuilder {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDocumentBuilder.class);
  private static final String TEMPLATE_DOC = "template.doc";


  Document doMailMerge(Page<Issue> listIssue, String name, Header header){
    Document document = getDocumentFromTemplate();
    mergeHeader(name, header, document);
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

  private void mergeHeader(String name, Header header, Document document) {
    try {
      document.getMailMerge().execute(new String[]{Header.FIELD_REF, Header.FIELD_AUTHOR, Header.FIELD_DATE, Header.FIELD_PRODUCT, Header.FIELD_VERSION},
          new Object[]{name, header.getAuthor(), header.getDate(), header.getProduct(), header.getVersion()});
    } catch (Exception e) {
      LOGGER.error("Header execute MailMerge problem", e);
      throw new IllegalStateException(e);
    }
  }

  private TableDataSource getTableDataSource(Page<Issue> listIssue) {
    IssueList issues = new IssueList();
    List<Issue> list = listIssue.getElements();
    for (Issue issue : list) {
      issues.add(new IssueTemp(issue.getSubject(),issue.getId()));
    }
    return new TableDataSource(issues);
  }

  private Document getDocumentFromTemplate() {
    Document document;
    try {
      File file = new File("test.doc");
      document = new Document(TEMPLATE_DOC);
      document.getMailMerge().setTrimWhitespaces(false);
    } catch (Exception e) {
      LOGGER.error("No template", e);
      throw new IllegalStateException(e);
    }
    return document;
  }

  File saveAsposeDocument(String name, Document doc, FileType type) throws Exception {
    String filename = name + "." + type.getExtension();
    File file = new File(filename);

    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
      doc.save(fileOutputStream, SaveOptions.createSaveOptions(filename));
    }

    return file;
  }

}
