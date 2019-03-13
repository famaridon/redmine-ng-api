package com.famaridon.redminengapi.services.indicators.impl.releasenote.documentbuilder;

import com.famaridon.redminengapi.services.indicators.beans.FileType;

public class DocumentBuilderFactory {
  public IDocumentBuilder getDocumentBuilder(FileType type){
    IDocumentBuilder documentBuilder;
    switch (type) {
      case DOC:
        documentBuilder = new DocDocumentBuilder();
        break;
      case ZIP:
        documentBuilder = new ZipDocumentBuilder();
        break;
      case PDF:
        documentBuilder = new PdfDocumentBuilder();
        break;
      default:
        throw new IllegalStateException();
    }
    return documentBuilder;
  }

}
