package com.famaridon.redminengapi.services.redmine.impl;

import com.famaridon.redminengapi.services.redmine.ProjectService;
import com.famaridon.redminengapi.services.redmine.rest.client.dto.ProjectRDto;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class DefaultProjectService extends AbstractRedmineService<Project> implements ProjectService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultProjectService.class);
	
	@Override
	public List<Project> findAll(String apiAccessKey) {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(this.configurationService.getRedmineServer(), apiAccessKey);
		
		try {
			return mgr.getProjectManager().getProjects();
		} catch (RedmineException e) {
			throw new IllegalStateException(e);
		}
	}
	
	@Override
	public ProjectRDto findById(String apiAccessKey, Long id) {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(this.configurationService.getRedmineServer(), apiAccessKey);
		try {
			return this.map(mgr.getProjectManager().getProjectById(Math.toIntExact(id)));
		} catch (RedmineException e) {
			throw new IllegalStateException(e);
		}
	}
	
	private ProjectRDto map(Project p) {
		ProjectRDto rDto = new ProjectRDto();
		rDto.id = p.getId();
		rDto.name = p.getName();
		rDto.identifier = p.getIdentifier();
		rDto.description = p.getDescription();
		rDto.homepage = p.getHomepage();
//		rDto.status = ;
//		rDto.is_public;
		rDto.created_on = p.getCreatedOn();
		rDto.updated_on = p.getUpdatedOn();
		rDto.parent = p.getParentId();
//		rDto.trackers = new ArrayList<>();
//		rDto.issue_categories = new ArrayList<>();
		
		return rDto;
	}
	
}
