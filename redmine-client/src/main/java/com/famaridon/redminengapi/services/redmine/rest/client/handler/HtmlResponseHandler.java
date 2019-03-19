package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HtmlResponseHandler extends AbstractRedmineResponseHandler<Document> {

  protected final String baseUri;

  public HtmlResponseHandler(String baseUri) {
    this.baseUri = baseUri;
  }
  
  protected ContentType getExpectedMimeType() {
    return ContentType.TEXT_HTML;
  }
  
  @Override
  protected Document parse(HttpEntity entity, ContentType contentType) throws IOException {
    return Jsoup.parse(entity.getContent(), contentType.getCharset().name(), this.baseUri);
  }
}
