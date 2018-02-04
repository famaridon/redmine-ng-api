package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;

import java.io.IOException;
import java.util.Set;

public interface StatusService {
	Set<Status> findAvailbaleByTrackerForNew(String apiKey, Long tracker) throws IOException;
	Set<Status> findAvailbaleByTrackerAndStatus(String apiKey, Long tracker, Long status) throws IOException;
}
