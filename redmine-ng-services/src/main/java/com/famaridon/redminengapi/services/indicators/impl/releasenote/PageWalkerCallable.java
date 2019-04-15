package com.famaridon.redminengapi.services.indicators.impl.releasenote;

import com.famaridon.redminengapi.services.redmine.Pager;
import com.famaridon.redminengapi.services.redmine.rest.client.beans.Page;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 *
 * @param <T>
 */
class PageWalkerCallable<T> implements Callable<List<T>> {

  public static final int DEFAULT_PAGE_SIZE = 25;
  private final PageFinder<T> pageFinder;
  private final Integer pageSize;

  public PageWalkerCallable(PageFinder<T> pageFinder) {
    this(pageFinder, DEFAULT_PAGE_SIZE);
  }
  public PageWalkerCallable(PageFinder<T> pageFinder, Integer pageSize) {
    this.pageFinder = pageFinder;
    this.pageSize = pageSize;
  }

  @Override
  public List<T> call() throws IOException {
    Optional<Pager> optionalPager = Optional.of(new Pager());
    Page<T> page = pageFinder.find(optionalPager.get());
    List<T> result = new ArrayList<>();
    result.addAll(page.getElements());
    optionalPager = page.nextPager();
    while (optionalPager.isPresent()) {
      page = pageFinder.find(optionalPager.get());
      result.addAll(page.getElements());
      optionalPager = page.nextPager();
    }
    return result;
  }
}
