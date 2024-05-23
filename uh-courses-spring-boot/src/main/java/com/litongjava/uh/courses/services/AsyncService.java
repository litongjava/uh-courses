package com.litongjava.uh.courses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

  @Autowired
  private SpiderService spiderService;

  @Async
  public CompletableFuture<String> updateClassesFromUrl(String url) {
    spiderService.updateClassesFromUrl(url);
    return CompletableFuture.completedFuture("Task finished");
  }

  @Async
  public CompletableFuture<String> spiderCourseToDbForKap(String semesterName) {
    spiderService.spiderCourseToDbForKap(semesterName);
    return CompletableFuture.completedFuture("Task finished");
  }
}
