package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;

import java.io.IOException;

public interface ObjectiveService {

    Page<Objective> findAll(Pager pager);

}
