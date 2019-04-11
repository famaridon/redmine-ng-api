package com.famaridon.redminengapi.services.indicators.impl.releasenote;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PageWalker {

  public <T> Future<List<T>> walk(PageFinder<T> pageFinder) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<List<T>> listFuture = executorService.submit(new PageWalkerCallable<>(pageFinder));
    executorService.shutdown();
    return listFuture;
  }

}