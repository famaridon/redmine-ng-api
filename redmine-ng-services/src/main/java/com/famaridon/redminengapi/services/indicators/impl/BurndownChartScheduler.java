package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.domain.entities.BurndownChartEntity;
import com.famaridon.redminengapi.domain.entities.ChartTimedValueEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.repositories.BurndownChartRepository;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.indicators.BurndownChartService;
import com.famaridon.redminengapi.services.indicators.beans.BurndownChart;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapper;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapperImpl;
import com.famaridon.redminengapi.services.redmine.Filter;
import com.famaridon.redminengapi.services.redmine.FilterFactory;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.QueryService;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.CustomField;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
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

/**
 * this class is moovapps process team specific
 */
@Singleton
public class BurndownChartScheduler {

  private static final Logger LOGGER = LoggerFactory.getLogger(BurndownChartScheduler.class);

  @Inject
  private ConfigurationService configurationService;
  @Inject
  private BurndownChartRepository burndownChartRepository;
  @Inject
  private IterationRepository iterationRepository;
  @Inject
  private IssueService issueService;

  private FilterFactory filterFactory;

  public BurndownChartScheduler() {
    this.filterFactory = new FilterFactory();
  }

  @Lock(LockType.WRITE)
  @Schedule(second = "*", persistent = false)
  @Transactional(TxType.REQUIRED)
  private void scheduled() {

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
        // TODO : create BurndownChartEntity
        burndownChartEntity = new BurndownChartEntity();
        burndownChartEntity.setIteration(iterationEntityOptional.get());

        ChartTimedValueEntity firtsPoint = new ChartTimedValueEntity();
        firtsPoint.setDate(iterationEntityOptional.get().getStart().atStartOfDay());
        firtsPoint
            .setValue(this.countAllOpenPoints(iterationEntityOptional.get(), this::sumAllPoints));
        burndownChartEntity.getValues().add(firtsPoint);
      }

      ChartTimedValueEntity firtsPointWithProgress = new ChartTimedValueEntity();
      firtsPointWithProgress.setDate(LocalDateTime.now());
      firtsPointWithProgress.setValue(
          this.countAllOpenPoints(iterationEntityOptional.get(), this::sumAllPointsWithProgress));
      burndownChartEntity.getValues().add(firtsPointWithProgress);

      this.burndownChartRepository.save(burndownChartEntity);
    } catch (IOException e) {
      LOGGER.warn("Redmine backend not available!", e);
    }
  }

  private BigDecimal sumAllPointsWithProgress(BigDecimal count, Issue issue) {
    Long developmentCostField = this.configurationService
        .getLong("projects.process.custom-fields.development-cost");
    Optional<CustomField> customField = issue.findCustomFields(developmentCostField);
    if (customField.isPresent()) {
      BigDecimal dueRatio = BigDecimal.ONE
          .subtract(new BigDecimal(issue.getDoneRatio()).divide(new BigDecimal(100)));
      return count.add(dueRatio.multiply(new BigDecimal((String) customField.get().getValue())));
    }
    return count;
  }

  private BigDecimal sumAllPoints(BigDecimal count, Issue issue) {
    Long developmentCostField = this.configurationService
        .getLong("projects.process.custom-fields.development-cost");
    Optional<CustomField> customField = issue.findCustomFields(developmentCostField);
    if (customField.isPresent()) {
      return count.add(new BigDecimal((String) customField.get().getValue()));
    }
    return count;
  }

  public BigDecimal countAllOpenPoints(IterationEntity iterationEntity, IssueOperator issueOperator)
      throws IOException {
    String apiKey = this.configurationService.getString("projects.process.readonlyApiKey");
    Long projectId = this.configurationService.getLong("projects.process.project");
    Long iterationField = this.configurationService
        .getLong("projects.process.custom-fields.iteration");

    List<Long> validTrackerIds = this.configurationService
        .getList(Long.class, "projects.process.burndown.valid-trackers");

    List<Filter> filters = new ArrayList<>();
    filters.add(this.filterFactory.createProjectFilter(projectId));
    filters.add(
        this.filterFactory.createCustomFieldFilter(iterationField, iterationEntity.getNumber()));

    validTrackerIds.forEach(trackerId -> {
      filters.add(this.filterFactory.createTrackerFilter(trackerId));
    });

    BigDecimal count = BigDecimal.ZERO;

    Pager pager = new Pager();
    Page<Issue> page = null;
    do {
      if (page != null) {
        pager = new Pager(page.getOffset() + page.getElements().size(), page.getLimit());
      }
      page = this.issueService.findAllByFilters(apiKey, filters, pager);
      for (Issue issue : page.getElements()) {
        count = issueOperator.operation(count, issue);
      }
    }
    while (page.hasNextPage());

    return count;
  }

  private interface IssueOperator {

    public BigDecimal operation(BigDecimal previous, Issue issue);

  }
}
