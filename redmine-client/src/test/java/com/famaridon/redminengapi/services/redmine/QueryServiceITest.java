package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.impl.DefaultQueryService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.IOException;

@RunWith(Arquillian.class)
public class QueryServiceITest extends AbstractServiceITest {
	
	@EJB
	private QueryService queryService;

	@Deployment
	public static WebArchive createDeployment() {
		return prepareDeployment()
				.addClass(QueryServiceITest.class)
				.addClass(QueryService.class)
				.addClass(DefaultQueryService.class);
	}

	@Test
	public void findAll() throws IOException {
	this.queryService.findAll(this.apiKey, new Pager());
	}
}
