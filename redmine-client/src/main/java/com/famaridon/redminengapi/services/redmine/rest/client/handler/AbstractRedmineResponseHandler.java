package com.famaridon.redminengapi.services.redmine.rest.client.handler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.ContentType;

import java.io.IOException;

public abstract class AbstractRedmineResponseHandler<T> implements ResponseHandler<T> {
	
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
		if (!contentType.getMimeType().equals(getExpectedMimeType().getMimeType())) {
			throw new ClientProtocolException("Unexpected content type:" + contentType);
		}
		return parse(entity, contentType);
	}
	
	protected abstract ContentType getExpectedMimeType();
	
	protected abstract T parse(HttpEntity entity, ContentType contentType) throws IOException;
}
