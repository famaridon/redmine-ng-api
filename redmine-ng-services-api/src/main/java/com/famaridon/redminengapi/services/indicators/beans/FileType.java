package com.famaridon.redminengapi.services.indicators.beans;

public enum FileType {
  DOC("doc","application/msword"),
  PDF("pdf","application/pdf"),
  ZIP("zip","application/zip");

  private String extension;
  private String mimeType;

  FileType(String extension, String mimeType) {
    this.extension = extension;
    this.mimeType = mimeType;
  }

  public String getExtension() {
    return extension;
  }

  public String getMimeType() {
    return mimeType;
  }
}
