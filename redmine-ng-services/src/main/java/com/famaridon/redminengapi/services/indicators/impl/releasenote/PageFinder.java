package com.famaridon.redminengapi.services.indicators.impl.releasenote;

import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.io.IOException;

public interface PageFinder<T> {

  Page<T> find(Pager pager) throws IOException;

}
