package com.famaridon.redminengapi.services.indicators.scheduler;

import com.famaridon.redminengapi.domain.entities.BurndownChartEntity;
import com.famaridon.redminengapi.domain.entities.ChartTimedValueEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.repositories.BurndownChartRepository;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.indicators.impl.InternalBurndownChartService;
import com.famaridon.redminengapi.services.indicators.impl.issue.SumPointsOperator;
import com.famaridon.redminengapi.services.indicators.impl.issue.SumPointsWithProgressOperator;
import com.famaridon.redminengapi.services.redmine.StatusType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * TODO : this is a draft class to start collecting data. this class is moovapps process team specific
 */
@Singleton
public class BurndownChartScheduler {

  private static final Logger LOGGER = LoggerFactory.getLogger(BurndownChartScheduler.class);

  @Inject
  private ConfigurationService configurationService;
  @Inject
  private BurndownChartRepository burndownChartRepository;
  @Inject
  private InternalBurndownChartService internalBurndownChartService;
  @Inject
  private IterationRepository iterationRepository;


  @Lock(LockType.WRITE)
  @Schedule(hour = "*")
  @Transactional(TxType.REQUIRED)
  private void scheduled() {
    Long developmentCostField = this.configurationService.getLong("redmine.custom-fields.development-cost");
    SumPointsOperator sumPointsOperator = new SumPointsOperator(developmentCostField);
    SumPointsWithProgressOperator sumPointsWithProgressOperator = new SumPointsWithProgressOperator(developmentCostField);

    Optional<IterationEntity> iterationEntityOptional = this.iterationRepository
        .findCurrentIteration();
    if (!iterationEntityOptional.isPresent()) {
      LOGGER.warn("No current iteration found");
      return;
    }

    try {
      Optional<BurndownChartEntity> burndownChartEntityOptional = this.burndownChartRepository
          .findByIteration(iterationEntityOptional.get());
      BurndownChartEntity burndownChartEntity = null;
      if (burndownChartEntityOptional.isPresent()) {
        burndownChartEntity = burndownChartEntityOptional.get();
      } else {
        burndownChartEntity = new BurndownChartEntity();
        burndownChartEntity.setIteration(iterationEntityOptional.get());

        ChartTimedValueEntity firtsPoint = new ChartTimedValueEntity();
        firtsPoint.setDate(iterationEntityOptional.get().getStart().atStartOfDay());
        firtsPoint.setValue(this.internalBurndownChartService.agregateIssues(iterationEntityOptional.get(), StatusType.ALL,
            sumPointsOperator, BigDecimal.ZERO));
        burndownChartEntity.getValues().add(firtsPoint);
      }

      ChartTimedValueEntity firtsPointWithProgress = new ChartTimedValueEntity();
      firtsPointWithProgress.setDate(LocalDateTime.now());
      firtsPointWithProgress.setValue(this.internalBurndownChartService.agregateIssues(iterationEntityOptional.get(), StatusType.ALL,
          sumPointsWithProgressOperator, BigDecimal.ZERO));
      burndownChartEntity.getValues().add(firtsPointWithProgress);

      this.burndownChartRepository.save(burndownChartEntity);
    } catch (IOException e) {
      LOGGER.warn("Redmine backend not available!", e);
    }
  }

}
