package com.famaridon.redminengapi.rest.mapper;

import com.famaridon.redminengapi.rest.dto.BurndownChartDto;
import com.famaridon.redminengapi.rest.dto.CategoryDto;
import com.famaridon.redminengapi.rest.dto.ChartTimedValueDto;
import com.famaridon.redminengapi.rest.dto.IssueDto;
import com.famaridon.redminengapi.rest.dto.IterationDto;
import com.famaridon.redminengapi.rest.dto.MembershipDto;
import com.famaridon.redminengapi.rest.dto.ObjectiveDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.PriorityDto;
import com.famaridon.redminengapi.rest.dto.ProjectDto;
import com.famaridon.redminengapi.rest.dto.QueryDto;
import com.famaridon.redminengapi.rest.dto.StatusDto;
import com.famaridon.redminengapi.rest.dto.TrackerDto;
import com.famaridon.redminengapi.rest.dto.UserDto;
import com.famaridon.redminengapi.rest.dto.VersionDto;
import com.famaridon.redminengapi.services.indicators.beans.BurndownChart;
import com.famaridon.redminengapi.services.indicators.beans.ChartTimedValue;
import com.famaridon.redminengapi.services.indicators.beans.Iteration;
import com.famaridon.redminengapi.services.indicators.beans.Objective;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Category;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Issue;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Membership;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Priority;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Project;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Query;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Status;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Tracker;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.User;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Version;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = RefMapper.class)
public interface DtoMapper {

  @Mappings(@Mapping(target = "elements", ignore = true))
  PageDto pageToPageDto(Page page);

  @Mappings({
      @Mapping(target = "apiKey", ignore = true),
      @Mapping(target = "roles", ignore = true)}
  )
  UserDto userToUserDto(User project);

  ProjectDto projectToProjectDto(Project project);

  MembershipDto membershipToMembershipDto(Membership membership);

  QueryDto queryToQueryDto(Query query);

  TrackerDto trackerToTrackerDto(Tracker tracker);

  CategoryDto categoryToCategoryDto(Category category);

  @Mappings(@Mapping(target = "customFields", ignore = true))
  IssueDto issueToIssueDto(Issue issue);

  StatusDto statusToStatusDto(Status status);

  List<UserDto> userssToUsersDtos(Iterable<User> project);

  List<ProjectDto> projectsToProjectDtos(Iterable<Project> project);

  List<MembershipDto> membershipsToMembershipDtos(Iterable<Membership> memberships);

  List<QueryDto> queriesToQueryDtos(Iterable<Query> queries);

  List<IssueDto> issuesToIssueDtos(Iterable<Issue> issues);

  List<StatusDto> statusesToStatusDtos(Iterable<Status> statuses);

  List<PriorityDto> prioritiesToPriorityDtos(Iterable<Priority> priorities);

  List<VersionDto> versionsToVersionDtos(Iterable<Version> versions);

  IterationDto iterationToIterationDto(Iteration iteration);

  List<IterationDto> iterationsToIterationDtos(Iterable<Iteration> iterations);

  Iteration iterationDtoToIteration(IterationDto iterationDto);

  List<Iteration> iterationDtosToIterations(List<IterationDto> iterationDtos);

  ObjectiveDto objectiveToObjectiveDto(Objective objective);

  List<ObjectiveDto> objectivesToObjectiveDtos(List<Objective> objectives);

  Objective objectiveDtoToObjective(ObjectiveDto objectiveDto);

  List<Objective> objectiveDtoToObjective(List<ObjectiveDto> objectiveDtos);

  ChartTimedValue chartTimedValueDtoToChartTimedValue(ChartTimedValueDto chartTimedValueDto);

  ChartTimedValueDto chartTimedValueToChartTimedValueDto(ChartTimedValue chartTimedValue);

  BurndownChart burndownChartDtoToBurndownChart(BurndownChartDto burndownChartDto);

  BurndownChartDto burndownChartToBurndownChartDto(BurndownChart burndownChart);

}
