package com.famaridon.redminengapi.services.redmine;

import org.apache.http.client.utils.URIBuilder;

public interface QueryParamSerializable {

  void serialize(URIBuilder uriBuilder);

}
