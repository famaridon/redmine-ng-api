package com.famaridon.redminengapi.services.indicators;

import com.famaridon.redminengapi.services.indicators.beans.AbstractBean;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.util.List;
import java.util.Optional;

public interface CrudService<B extends AbstractBean> {

	B create(B bean);

	Page<B> findAll(Pager pager);
	Optional<B> findById(Long id);

	void update(B bean);

	void deleteById(Long id);
	void delete(B bean);

}
