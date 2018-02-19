package com.famaridon.redminengapi.rest.mapper;

import com.famaridon.redminengapi.rest.dto.CategoryDto;
import com.famaridon.redminengapi.rest.dto.IssueDto;
import com.famaridon.redminengapi.rest.dto.MembershipDto;
import com.famaridon.redminengapi.rest.dto.PageDto;
import com.famaridon.redminengapi.rest.dto.PriorityDto;
import com.famaridon.redminengapi.rest.dto.ProjectDto;
import com.famaridon.redminengapi.rest.dto.QueryDto;
import com.famaridon.redminengapi.rest.dto.StatusDto;
import com.famaridon.redminengapi.rest.dto.TrackerDto;
import com.famaridon.redminengapi.rest.dto.UserDto;
import com.famaridon.redminengapi.services.realtime.beans.RealtimeMessage;
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
import com.famaridon.redminengapi.websocket.dto.RealtimeMessageDto;
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
	
	RealtimeMessageDto realtimeMessageTorealtimeMessageDto(RealtimeMessage realtimeMessage);
	
	List<UserDto> userssToUsersDtos(Iterable<User> project);
	
	List<ProjectDto> projectsToProjectDtos(Iterable<Project> project);
	
	List<MembershipDto> membershipsToMembershipDtos(Iterable<Membership> memberships);
	
	List<QueryDto> queriesToQueryDtos(Iterable<Query> queries);
	
	List<IssueDto> issuesToIssueDtos(Iterable<Issue> issues);
	
	List<StatusDto> statusesToStatusDtos(Iterable<Status> statuses);
	
	List<PriorityDto> prioritiesToPriorityDtos(Iterable<Priority> priorities);
	
	
}
