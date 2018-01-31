package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;

import java.io.IOException;
import java.util.List;

public interface StatusService {
	List<Status> findAvailbaleByTracker(String apiKey, Long tracker) throws IOException;
	List<Status> findAvailbaleByTrackerAndStatus(String apiKey, Long tracker, Long status) throws IOException;
}
