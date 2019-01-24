package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.domain.entities.BurndownChartEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.repositories.BurndownChartRepository;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.services.indicators.BurndownChartService;
import com.famaridon.redminengapi.services.indicators.beans.BurndownChart;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapper;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapperImpl;
import java.util.Optional;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class BurndownChartScheduler {

  private static final Logger LOGGER = LoggerFactory.getLogger(BurndownChartScheduler.class);

  @Inject
  private BurndownChartRepository burndownChartRepository;
  @Inject
  private IterationRepository iterationRepository;


  public BurndownChartScheduler() {
  }

  @Lock(LockType.WRITE)
  @Schedule(hour = "*", persistent = false)
  @Transactional(TxType.REQUIRED)
  private void scheduled() {

    Optional<IterationEntity> iterationEntityOptional = this.iterationRepository.findCurrentIteration();
    if(!iterationEntityOptional.isPresent()) {
      LOGGER.warn("No current iteration found");
      return;
    }

    Optional<BurndownChartEntity> burndownChartEntityOptional = this.burndownChartRepository.findByIteration(iterationEntityOptional.get());
    BurndownChartEntity burndownChartEntity = null;
    if(burndownChartEntityOptional.isPresent()) {
      burndownChartEntity = burndownChartEntityOptional.get();
    } else {
      // TODO : create BurndownChartEntity
      // TODO : add iteration first ChartTimedValueEntity
    }

    // TODO : add progress ChartTimedValueEntity
  }

}
