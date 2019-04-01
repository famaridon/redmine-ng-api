package com.famaridon.redminengapi.services.cache;

import com.famaridon.redminengapi.services.cache.impl.InfinispanCacheService;
import com.famaridon.redminengapi.services.cache.interceptors.CacheInterceptor;
import com.famaridon.redminengapi.services.cache.interceptors.CachedCdiBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(Arquillian.class)
public class CacheInterceptorJEETest {

  @Inject
  protected CacheService cacheService;

  @Inject
  protected CachedCdiBean cachedCdiBean;

  public static WebArchive prepareDeployment() {

    return ShrinkWrap.create(WebArchive.class)
        .addPackages(true, "com.famaridon.redminengapi.services.cache.annotation")
        // add custom MANIFEST.MF to load infinispan module
        .addAsManifestResource(new File("src/test/resources/META-INF/MANIFEST.MF"))
        // add web.xml to tell wildfly to start infinispan clustering
        .addAsWebInfResource(new File("src/test/resources/WEB-INF/web.xml"))
        .addAsWebInfResource(new File("src/main/resources/META-INF/beans.xml"));
  }

  @Deployment
  public static WebArchive createDeployment() {
    return prepareDeployment()
        .addClass(CacheInterceptorJEETest.class)
        .addClass(CachedCdiBean.class)
        .addClass(CacheInterceptor.class)
        .addClass(CacheService.class)
        .addClass(InfinispanCacheService.class);
  }

  @Test
  public void cachePutDefault() {
    Double first = this.cachedCdiBean.random();
    assertEquals(first, this.cachedCdiBean.random());
  }

  @Test
  public void cachePutCustom() {
    Double first = this.cachedCdiBean.randomCustom();
    assertEquals(first, this.cachedCdiBean.randomCustom());
  }

  @Test
  public void cachePutWithKey() {
    Double first = this.cachedCdiBean.randomWithKey(10);
    assertEquals(first, this.cachedCdiBean.randomWithKey(10));
    assertNotEquals(first, this.cachedCdiBean.randomWithKey(20));
  }

  @Test
  public void cachePutWithKeys() {
    Double first = this.cachedCdiBean.randomWithKeys("hello", 10);
    assertEquals(first, this.cachedCdiBean.randomWithKeys("hello", 10));
    assertNotEquals(first, this.cachedCdiBean.randomWithKeys("hello", 20));
    assertNotEquals(first, this.cachedCdiBean.randomWithKeys("hello world", 10));
  }
}