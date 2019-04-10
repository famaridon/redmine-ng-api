package com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder;

import com.famaridon.redminengapi.services.indicators.beans.FileType;
import com.famaridon.redminengapi.services.indicators.beans.ZipFileWritter;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Version;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZipDocumentBuilder extends AbstractDocumentBuilder {

  private static final Logger LOGGER = LoggerFactory.getLogger(ZipDocumentBuilder.class);

  @Override
  public File build(Page<Issue> listIssue, Version version, FileType type) {
    DocumentBuilderFactory factory = new DocumentBuilderFactory();
    File filePdf = factory.getDocumentBuilder(FileType.PDF).build(listIssue, version, FileType.PDF);
    File fileDoc = factory.getDocumentBuilder(FileType.DOC).build(listIssue, version, FileType.DOC);
    try {
      String name = "RLN-" + setDate(new Date());
      ZipFileWritter zip = new ZipFileWritter(name + "." + type.getExtension());
      zip.addFile(filePdf.getName());
      zip.addFile(fileDoc.getName());
      zip.close();
      return new File(name + "." + type.getExtension());
    } catch (IOException e) {
      LOGGER.error("File not found", e);
      throw new IllegalStateException(e);
    }
  }


}
