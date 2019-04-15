package com.famaridon.redminengapi.services.indicators.impl;

import com.famaridon.redminengapi.domain.entities.BurndownChartEntity;
import com.famaridon.redminengapi.domain.entities.IterationEntity;
import com.famaridon.redminengapi.domain.repositories.BurndownChartRepository;
import com.famaridon.redminengapi.domain.repositories.IterationRepository;
import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.exceptions.ObjectNotFoundException;
import com.famaridon.redminengapi.services.indicators.BurndownChartService;
import com.famaridon.redminengapi.services.indicators.beans.BurndownChart;
import com.famaridon.redminengapi.services.indicators.beans.ChartTimedValue;
import com.famaridon.redminengapi.services.indicators.impl.issue.IssueOperator;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapper;
import com.famaridon.redminengapi.services.indicators.mapper.IndicatorsEntityMapperImpl;
import com.famaridon.redminengapi.services.redmine.Filter;
import com.famaridon.redminengapi.services.redmine.FilterFactory;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.StatusType;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
@Default
public class DefaultBurndownChartService extends
    AbstractCrudRepositoryService<BurndownChartRepository, BurndownChartEntity, BurndownChart> implements
    BurndownChartService, InternalBurndownChartService {

  @Inject
  private IndicatorsEntityMapper indicatorsEntityMapper;
  @Inject
  private ConfigurationService configurationService;
  @Inject
  private BurndownChartRepository burndownChartRepository;
  @Inject
  private IterationRepository iterationRepository;
  @Inject
  private IssueService issueService;
  @Inject
  private FilterFactory filterFactory;

  public DefaultBurndownChartService() {
  }

  public DefaultBurndownChartService(BurndownChartRepository burndownChartRepository) {
    this.burndownChartRepository = burndownChartRepository;
    this.indicatorsEntityMapper = new IndicatorsEntityMapperImpl();
  }

  @Override
  public Optional<BurndownChart> findByIteration(Long iterationId) throws ObjectNotFoundException {
    Optional<IterationEntity> iterationEntityOptional = this.iterationRepository.findById(iterationId);
    if (!iterationEntityOptional.isPresent()) {
      throw new ObjectNotFoundException("No iteration found for id " + iterationId);
    }
    Optional<BurndownChartEntity> burndownChartEntityOptional = this.burndownChartRepository
        .findByIteration(iterationEntityOptional.get());

    if (!burndownChartEntityOptional.isPresent()) {
      return Optional.empty();
    }

    return Optional.of(this.indicatorsEntityMapper.burndownChartEntityToBurndownChart(burndownChartEntityOptional.get()));
  }

  @Override
  public BurndownChart buildIdealIteration(Long iterationId) throws ObjectNotFoundException {
    Optional<IterationEntity> iterationEntityOptional = this.iterationRepository.findById(iterationId);
    if (!iterationEntityOptional.isPresent()) {
      throw new ObjectNotFoundException("No iteration found for id " + iterationId);
    }

    BurndownChart ideal = new BurndownChart();
    ideal.setIteration(this.indicatorsEntityMapper.iterationEntityToIteration(iterationEntityOptional.get()));
    ideal.setId(-1L);
    ideal.setName("Ideal iteration " + iterationEntityOptional.get().getId());

    LocalDate startDate = iterationEntityOptional.get().getStart();
    LocalDate endDate = iterationEntityOptional.get().getEnd().plus(1, ChronoUnit.DAYS);
    long iterationDays = startDate.until(endDate, ChronoUnit.DAYS);
    BigDecimal iterationWorkindays = BigDecimal.ZERO;
    for (int i = 0; i < iterationDays; i++) {
      LocalDate loopDate = startDate.plus(i, ChronoUnit.DAYS);
      if (this.isWorkingDayOfWeek(loopDate.getDayOfWeek())) {
        iterationWorkindays = iterationWorkindays.add(BigDecimal.ONE);
      }
    }

    BigDecimal startPoint = iterationEntityOptional.get().getPlannedDevelopmentCost();
    // we must have a = (Yb-Ya) / (Xb - Xa)
    BigDecimal a = startPoint.subtract(BigDecimal.ZERO).divide(iterationWorkindays.subtract(BigDecimal.ZERO), MathContext.DECIMAL32)
        .multiply(BigDecimal.valueOf(-1));
    BigDecimal x = BigDecimal.ZERO;
    for (int i = 0; i <= iterationDays; i++) {
      LocalDate loopDate = startDate.plus(i, ChronoUnit.DAYS);
      ChartTimedValue point = new ChartTimedValue();
      point.setDate(loopDate.atStartOfDay());
      if (i != 0 && !this.isNonProgressDayOfWeek(loopDate.getDayOfWeek())) {
        point.setValue(ideal.getValues().get(ideal.getValues().size() - 1).getValue());
      } else {
        point.setValue(a.multiply(x).add(startPoint));
        x = x.add(BigDecimal.ONE);
      }
      ideal.getValues().add(point);
    }

    return ideal;
  }

  private boolean isNonProgressDayOfWeek(DayOfWeek dayOfWeek) {
    return !(dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.MONDAY);
  }

  private boolean isWorkingDayOfWeek(DayOfWeek dayOfWeek) {
    return !(dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY);
  }

  public <T> T agregateIssues(IterationEntity iterationEntity, StatusType statusType, IssueOperator<T> issueOperator, T initial)
      throws IOException {
    String apiKey = this.configurationService.getString("redmine.readonlyApiKey");
    Long projectId = this.configurationService.getLong("redmine.projects.process.project");
    Long iterationField = this.configurationService
        .getLong("redmine.custom-fields.iteration");

    List<Long> validTrackerIds = this.configurationService
        .getList(Long.class, "redmine.projects.process.burndown.valid-trackers");

    List<Filter> filters = new ArrayList<>();
    filters.add(this.filterFactory.createStatusFilter(statusType));
    filters.add(this.filterFactory.createProjectFilter(projectId));
    filters.add(this.filterFactory.createCustomFieldFilter(iterationField, iterationEntity.getNumber()));
    filters.add(this.filterFactory.createTrackerFilter(validTrackerIds));

    T result = initial;

    Pager pager = new Pager(0L, 100L);
    Page<Issue> page = null;
    do {
      if (page != null) {
        pager = new Pager(page.getOffset() + page.getElements().size(), page.getLimit());
      }
      page = this.issueService.findAllByFilters(apiKey, filters, pager);
      for (Issue issue : page.getElements()) {
        result = issueOperator.operation(result, issue);
      }
    }
    while (page.hasNextPage());

    return result;
  }

  @Override
  protected BurndownChartRepository getRepository() {
    return this.burndownChartRepository;
  }

  @Override
  protected BurndownChart map(BurndownChartEntity entity) {
    return this.indicatorsEntityMapper.burndownChartEntityToBurndownChart(entity);
  }

  @Override
  protected BurndownChartEntity map(BurndownChart bean) {
    return this.indicatorsEntityMapper.burndownChartToBurndownChartEntity(bean);
  }

  @Override
  protected void merge(BurndownChart bean, BurndownChartEntity entity) {
    this.indicatorsEntityMapper.updateBurndownChartEntityFromBurndownChart(bean, entity);
  }
}
