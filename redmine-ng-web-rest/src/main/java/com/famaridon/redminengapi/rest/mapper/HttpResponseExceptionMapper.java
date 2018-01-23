package com.famaridon.redminengapi.rest.mapper;

import org.apache.http.client.HttpResponseException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class HttpResponseExceptionMapper implements ExceptionMapper<HttpResponseException> {
	
	@Override
	public Response toResponse(HttpResponseException exception) {
		return Response.status(exception.getStatusCode()).build();
	}
}
