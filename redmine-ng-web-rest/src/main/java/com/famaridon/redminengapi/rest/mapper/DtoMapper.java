package com.famaridon.redminengapi.rest.mapper;

import com.famaridon.redminengapi.rest.dto.*;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface DtoMapper {
	
	@Mappings(@Mapping(target = "elements", ignore = true))
	PageDto pageToPageDto(Page page);
	
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
	
	
}
