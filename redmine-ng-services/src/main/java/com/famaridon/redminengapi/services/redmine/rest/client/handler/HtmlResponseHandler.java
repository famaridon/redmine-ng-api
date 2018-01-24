package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import com.famaridon.redminengapi.services.ConfigurationService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.ContentType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HtmlResponseHandler implements ResponseHandler<Document> {
	
	protected final ConfigurationService configurationService;
	
	public HtmlResponseHandler(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
	@Override
	public Document handleResponse(HttpResponse response) throws IOException {
		StatusLine statusLine = response.getStatusLine();
		HttpEntity entity = response.getEntity();
		if (statusLine.getStatusCode() >= 300) {
			throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		}
		if (entity == null) {
			throw new ClientProtocolException("Response contains no content");
		}
		
		ContentType contentType = ContentType.getOrDefault(entity);
		if (!contentType.getMimeType().equals(ContentType.TEXT_HTML.getMimeType())) {
			throw new ClientProtocolException("Unexpected content type:" + contentType);
		}
		
		return Jsoup.parse(entity.getContent(),contentType.getCharset().name(), configurationService.buildUrl(""));
		
	}
}