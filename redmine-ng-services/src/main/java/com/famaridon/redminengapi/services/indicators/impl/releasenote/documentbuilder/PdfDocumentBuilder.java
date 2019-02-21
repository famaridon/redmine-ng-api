package com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder;

import com.aspose.words.Document;
import com.famaridon.redminengapi.services.indicators.beans.FileType;
import com.famaridon.redminengapi.services.indicators.beans.Header;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.io.File;
import java.io.FileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PdfDocumentBuilder extends AbstractDocumentBuilder{

  private static final Logger LOGGER = LoggerFactory.getLogger(PdfDocumentBuilder.class);


  @Override
  public File getFile(Page<Issue> listIssue, String name, Header header) {
    Document document = doMailMerge(listIssue,name,header);
    try {
      return saveAsposeDocument(name, document, FileType.PDF);
    } catch (FileNotFoundException e) {
      LOGGER.error("File not found", e);
      throw new IllegalStateException(e);
    } catch (Exception e) {
      LOGGER.error("Cannot save document", e);
      throw new IllegalStateException(e);
    }
  }

}
