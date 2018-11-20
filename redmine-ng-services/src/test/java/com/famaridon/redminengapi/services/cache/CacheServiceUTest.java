package com.famaridon.redminengapi.services.cache;

import com.famaridon.redminengapi.services.cache.impl.InfinispanCacheService;
import com.famaridon.redminengapi.services.configuration.ConfigurationService;
import com.famaridon.redminengapi.services.configuration.impl.DefaultConfigurationService;
import com.famaridon.redminengapi.services.redmine.AbstractServiceITest;
import com.famaridon.redminengapi.services.redmine.IssueService;
import com.famaridon.redminengapi.services.redmine.IssueServiceITest;
import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.impl.AbstractRedmineService;
import com.famaridon.redminengapi.services.redmine.impl.DefaultIssueService;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.strategy.RejectDependenciesStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.io.File;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CacheServiceUTest {

    public static WebArchive prepareDeployment() {

        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.famaridon.redminengapi.services.cache.annotation")
                // add custom MANIFEST.MF to load infinispan module
                .addAsManifestResource(new File("src/test/resources/META-INF/MANIFEST.MF"))
                // add web.xml to tell wildfly to start infinispan clustering
                .addAsWebInfResource(new File("src/test/resources/WEB-INF/web.xml"))
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    protected CacheService cacheService;

    @Deployment
    public static WebArchive createDeployment() {
        return prepareDeployment()
                .addClass(CacheServiceUTest.class)
                .addClass(CacheService.class)
                .addClass(InfinispanCacheService.class);
    }

    @Test
    public void getDefaultCache() {
        Cache<String, String> cache = cacheService.getCache("default");
        assertNotNull(cache);
    }

    @Test
    public void getCustomCache() {
        Cache<String, String> cache = cacheService.getCache("custom");
        assertNotNull(cache);
    }

    @Test
    public void getCustomCacheWithConfiguration() {
        Cache<String, String> cache = cacheService.getCache("customCacheWithConfiguration", new ConfigurationBuilder().expiration().lifespan(100l).build());
        assertNotNull(cache);
    }

}