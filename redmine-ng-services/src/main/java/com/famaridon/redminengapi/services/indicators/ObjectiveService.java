package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.indicators.beans.Objective;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;

public interface ObjectiveService {

    Page<Objective> findAll(Pager pager);
    
    Objective create(Objective objective);
    
    Objective findById(Long id);
    
    void update(Objective objective);
    
    void delete(Objective objective);

}
