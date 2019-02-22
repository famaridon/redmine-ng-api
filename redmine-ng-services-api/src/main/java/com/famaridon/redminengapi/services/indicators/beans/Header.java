package com.famaridon.redminengapi.services.indicators.beans;

public class Header {
  public static final String FIELD_REF="Ref";
  public static final String FIELD_AUTHOR="Auteur";
  public static final String FIELD_DATE="Date";
  public static final String FIELD_PRODUCT="Produit";
  public static final String FIELD_VERSION="Version";
  private String name;
  private String author;
  private String date;
  private String product;
  private String version;

  public Header(String name, String author, String date, String product, String version) {
    this.name = name;
    this.author = author;
    this.date = date;
    this.product = product;
    this.version = version;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}
