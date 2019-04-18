package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Version;

import java.io.IOException;

public interface VersionsService
{
	Page<Version> findAll(String apiKey, Long project) throws IOException;
	Version findById (String apiKey, Long version) throws IOException;
}
