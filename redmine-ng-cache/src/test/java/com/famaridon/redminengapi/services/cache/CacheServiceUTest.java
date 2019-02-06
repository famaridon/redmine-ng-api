package com.famaridon.redminengapi.services.cache;

import static org.junit.Assert.assertNotNull;

import com.famaridon.redminengapi.services.cache.impl.InfinispanCacheService;
import java.io.File;
import javax.inject.Inject;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CacheServiceUTest {

  @Inject
  protected CacheService cacheService;

  public static WebArchive prepareDeployment() {

    return ShrinkWrap.create(WebArchive.class)
        .addPackages(true, "com.famaridon.redminengapi.services.cache.annotation")
        // add custom MANIFEST.MF to load infinispan module
        .addAsManifestResource(new File("src/test/resources/META-INF/MANIFEST.MF"))
        // add web.xml to tell wildfly to start infinispan clustering
        .addAsWebInfResource(new File("src/test/resources/WEB-INF/web.xml"))
        .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
  }

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
    Cache<String, String> cache = cacheService
        .getCache("customCacheWithConfiguration", new ConfigurationBuilder().expiration().lifespan(100l).build());
    assertNotNull(cache);
  }

}