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
        //Est-ce qu'un throw peut se faire dans un default d'un switch?
        throw new IllegalStateException();
    }
    return documentBuilder;
  }

}
