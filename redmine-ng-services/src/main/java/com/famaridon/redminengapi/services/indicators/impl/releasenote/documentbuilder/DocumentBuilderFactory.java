package com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder;

import com.famaridon.redminengapi.services.indicators.beans.FileType;

public class DocumentBuilderFactory {
  public DocumentBuilder getDocumentBuilder(FileType type){
    DocumentBuilder documentBuilder;
    switch (type) {
      case DOC:
        documentBuilder = new FileDocumentBuilder();
        break;
      case PDF:
        documentBuilder = new FileDocumentBuilder();
        break;
      case ZIP:
        documentBuilder = new ZipDocumentBuilder();
        break;
      default:
        throw new IllegalStateException();
    }
    return documentBuilder;
  }

}
