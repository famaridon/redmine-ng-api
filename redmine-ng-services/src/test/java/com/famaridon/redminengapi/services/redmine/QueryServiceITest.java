package com.famaridon.redminengapi.services.redmine;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.IOException;

@RunWith(Arquillian.class)
public class QueryServiceITest extends AbstractServiceITest {
	
	@EJB
	private QueryService queryService;
	
	@Test
	public void findAll() throws IOException {
	this.queryService.findAll(this.apiKey, new Pager());
	}
}
