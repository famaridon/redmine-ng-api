package com.famaridon.redminengapi.domain.repositories.impl;

import com.famaridon.redminengapi.domain.entities.BurndownChartEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.entities.ObjectiveEntity;
import com.famaridon.redminengapi.domain.repositories.BurndownChartRepository;
import com.famaridon.redminengapi.domain.repositories.ObjectiveRepository;
import javax.ejb.Stateful;
import javax.persistence.TypedQuery;

@Stateful
public class JPABurndownChartRepository extends AbstractJPARepository<BurndownChartEntity> implements
		BurndownChartRepository {
	
	@Override
	protected Class<BurndownChartEntity> getClazz() {
		return BurndownChartEntity.class;
	}


}
