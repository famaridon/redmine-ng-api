package com.famaridon.redminengapi.services.redmine;

import com.famaridon.redminengapi.services.redmine.filter.AbstractFilter;
import com.famaridon.redminengapi.services.redmine.impl.DefaultFilterFactory;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.AbstractRedmineBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@RunWith(Arquillian.class)
public class FilterFactoryUTest {
	
	@Inject
	private FilterFactory filterFactory;

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive webArchive = ShrinkWrap.create(WebArchive.class)
			.addClass(FilterFactory.class)
			.addClass(DefaultFilterFactory.class)
			.addClass(QueryParamSerializable.class)
			.addClass(Filter.class)
			.addPackage(AbstractFilter.class.getPackage())
			.addPackages(true, AbstractRedmineBean.class.getPackage())
			.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
		
		System.out.println(webArchive.toString(true));
		return webArchive;
	}

	@Test
	public void findAll() throws IOException {
		this.filterFactory.createStatusFilter(10l);
	}
}
