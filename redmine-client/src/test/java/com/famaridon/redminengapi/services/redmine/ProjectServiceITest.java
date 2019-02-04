package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.DefaultProjectService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.io.IOException;

@RunWith(Arquillian.class)
public class ProjectServiceITest extends AbstractServiceITest{

	@Inject
	ProjectService projectService;

	@Deployment
	public static WebArchive createDeployment() {
		return prepareDeployment()
				.addClass(ProjectServiceITest.class)
				.addClass(ProjectService.class)
				.addClass(DefaultProjectService.class);
	}

	@Test
	public void findById() throws IOException {
		this.projectService.findById(this.apiKey, 372L);
	}
	
	@Test
	public void findAll() throws IOException {
		this.projectService.findAll(this.apiKey, new Pager());
	}
}
