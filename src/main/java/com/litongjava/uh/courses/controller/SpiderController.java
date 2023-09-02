package com.litongjava.uh.courses.controller;

import com.litongjava.uh.courses.services.AsyncService;
import com.litongjava.uh.courses.services.SpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by litonglinux@qq.com on 2023/6/21_1:56
 */
@RequestMapping("spider")
@RestController
@Slf4j
@CrossOrigin
public class SpiderController {

  @Autowired
  private AsyncService asyncService;

  @RequestMapping("kap")
  public ResponseEntity<String> kap(String semesterName) {
    CompletableFuture<String> future = asyncService.spiderCourseToDbForKap(semesterName);
    return ResponseEntity.ok("Task started");
  }

  @GetMapping("/kapForWait")
  public ResponseEntity<String> getAsyncResult() {
    // 在实际使用中,你需要妥善存储和获取Future对象
    // 这里仅仅是一个简化的示例

    String result;
    try {
      CompletableFuture<String> future = asyncService.spiderCourseToDbForKap(null);
      result = future.get(); // get()方法会阻塞直到任务完成
    } catch (InterruptedException | ExecutionException e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while getting the result");
    }

    return ResponseEntity.ok(result);
  }

  @RequestMapping("/updateClassesFromUrl")
  public ResponseEntity<Boolean> updateClassesFromUrl(String url) {
    try {
      CompletableFuture<String> future = asyncService.updateClassesFromUrl(url);
      future.get(); // get()方法会阻塞直到任务完成
      return ResponseEntity.ok(true);
    } catch (Exception e) {
      return ResponseEntity.ok(false);
    }
  }
}
