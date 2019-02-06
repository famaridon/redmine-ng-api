package com.famaridon.redminengapi.rest.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SecurityExceptionMapper implements ExceptionMapper<SecurityException> {
	
	@Override
	public Response toResponse(SecurityException e) {
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}
}
