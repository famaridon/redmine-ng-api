package com.famaridon.redminengapi.services.redmine;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.IOException;

@RunWith(Arquillian.class)
public class ProjectServiceITest extends AbstractServiceITest{

	@EJB
	ProjectService projectService;
	
	@Test
	public void findById() throws IOException {
		this.projectService.findById(this.apiKey, 372L);
	}
	
	@Test
	public void findAll() throws IOException {
		this.projectService.findAll(this.apiKey, new Pager());
	}
}
