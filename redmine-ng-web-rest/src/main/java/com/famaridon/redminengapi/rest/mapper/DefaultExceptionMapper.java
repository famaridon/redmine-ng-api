package com.famaridon.redminengapi.rest.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DefaultExceptionMapper implements ExceptionMapper<Exception> {
	
	private static final Logger LOG = LoggerFactory.getLogger(DefaultExceptionMapper.class);
	
	@Override
	public Response toResponse(Exception exception) {
		LOG.error("Unsuported error occur", exception);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
}
