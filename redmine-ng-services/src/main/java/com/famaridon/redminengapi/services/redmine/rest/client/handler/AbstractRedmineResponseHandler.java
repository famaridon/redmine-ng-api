package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.ContentType;

import java.io.IOException;

public abstract class AbstractRedmineResponseHandler<T> implements ResponseHandler<T> {
	
	protected final ConfigurationService configurationService;
	
	protected AbstractRedmineResponseHandler(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	
	@Override
	public T handleResponse(HttpResponse response) throws IOException {
		StatusLine statusLine = response.getStatusLine();
		HttpEntity entity = response.getEntity();
		if (statusLine.getStatusCode() >= 300) {
			throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
		}
		if (entity == null) {
			throw new ClientProtocolException("Response contains no content");
		}
		
		ContentType contentType = ContentType.getOrDefault(entity);
		if (!contentType.getMimeType().equals(ContentType.APPLICATION_JSON.getMimeType())) {
			throw new ClientProtocolException("Unexpected content type:" + contentType);
		}
		return parse(entity);
		
	}
	
	protected abstract T parse(HttpEntity entity) throws IOException;
}
