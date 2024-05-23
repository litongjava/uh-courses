package com.litongjava.uh.courses.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.tio.http.server.annotation.EnableCORS;
import com.litongjava.tio.http.server.annotation.RequestPath;
import com.litongjava.tio.utils.resp.RespVo;
import com.litongjava.uh.courses.services.AsyncService;

/**
 * Created by litonglinux@qq.com on 2023/6/21_1:56
 */
@RequestPath("/spider")
@EnableCORS
public class SpiderController {

  private AsyncService asyncService = Aop.get(AsyncService.class);

  @RequestPath("/kap")
  public RespVo kap(String semesterName) {
    CompletableFuture<String> future = asyncService.spiderCourseToDbForKap(semesterName);
    return RespVo.ok("task satred:" + future.toString());
  }

  @RequestPath("/kapForWait")
  public RespVo getAsyncResult() {
    // 在实际使用中,你需要妥善存储和获取Future对象
    // 这里仅仅是一个简化的示例

    String result;
    try {
      CompletableFuture<String> future = asyncService.spiderCourseToDbForKap(null);
      result = future.get(); // get()方法会阻塞直到任务完成
    } catch (InterruptedException | ExecutionException e) {
      return RespVo.fail("Error while getting the result");
    }

    return RespVo.ok(result);
  }

  @RequestPath("/updateClassesFromUrl")
  public RespVo updateClassesFromUrl(String url) {
    try {
      CompletableFuture<String> future = asyncService.updateClassesFromUrl(url);
      future.get(); // get()方法会阻塞直到任务完成
      return RespVo.ok(true);
    } catch (Exception e) {
      return RespVo.ok(false);
    }
  }
}
