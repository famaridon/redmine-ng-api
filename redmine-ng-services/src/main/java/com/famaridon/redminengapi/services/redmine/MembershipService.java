package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.rest.client.beans.Membership;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;

import java.io.IOException;

public interface MembershipService {
	
	Page<Membership> findByProject(String apiKey, Long id) throws IOException;
}
