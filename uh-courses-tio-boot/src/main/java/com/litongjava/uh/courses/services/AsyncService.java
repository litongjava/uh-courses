package com.litongjava.uh.courses.services;

import java.util.concurrent.CompletableFuture;

public class AsyncService {

  private SpiderService spiderService;

  public CompletableFuture<String> updateClassesFromUrl(String url) {
    spiderService.updateClassesFromUrl(url);
    return CompletableFuture.completedFuture("Task finished");
  }

  public CompletableFuture<String> spiderCourseToDbForKap(String semesterName) {
    spiderService.spiderCourseToDbForKap(semesterName);
    return CompletableFuture.completedFuture("Task finished");
  }
}
