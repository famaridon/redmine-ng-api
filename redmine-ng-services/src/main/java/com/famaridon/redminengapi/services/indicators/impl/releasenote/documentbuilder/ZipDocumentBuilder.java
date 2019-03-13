package com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder;

import com.aspose.words.Document;
import com.famaridon.redminengapi.services.indicators.beans.FileType;
import com.famaridon.redminengapi.services.indicators.beans.Header;
import com.famaridon.redminengapi.services.indicators.beans.ZipFileWritter;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipDocumentBuilder extends AbstractDocumentBuilder {

  private static final Logger LOGGER = LoggerFactory.getLogger(ZipDocumentBuilder.class);

  @Override
  public File getFile(Page<Issue> listIssue, String name, Header header) {
    Document document = doMailMerge(listIssue,name,header);
    try {
      saveAsposeDocument(name, document, FileType.PDF);
      saveAsposeDocument(name, document, FileType.DOC);
    } catch (FileNotFoundException e) {
      LOGGER.error("File not found", e);
      throw new IllegalStateException(e);
    } catch (Exception e) {
      LOGGER.error("Cannot save document", e);
      throw new IllegalStateException(e);
    }
    try {
      ZipFileWritter zip = new ZipFileWritter(name+"."+FileType.ZIP.getExtension());
      zip.addFile(name+"."+FileType.PDF.getExtension());
      zip.addFile(name+"."+FileType.DOC.getExtension());
      zip.close();
    } catch (IOException e) {
      LOGGER.error("Archive problem", e);
      throw new IllegalStateException(e);
    }
    File zipFile=new File(name+"."+FileType.ZIP.getExtension());
    return zipFile;
  }


}
