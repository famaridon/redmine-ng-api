package com.famaridon.redminengapi.services.redmine.rest.client.exceptions;

public class RedmineClientException extends Exception {
	
	private final ClientExceptionCode code;
	private final int remoteStatusCode;
	
	public RedmineClientException(ClientExceptionCode code, int remoteStatusCode) {
		this(code,remoteStatusCode, null);
	}
	
	public RedmineClientException(ClientExceptionCode code,int remoteStatusCode, Throwable cause) {
		super(code.toString(), cause);
		this.code = code;
		this.remoteStatusCode = remoteStatusCode;
	}
	
	public int getRemoteStatusCode() {
		return this.remoteStatusCode;
	}
}
