package com.litongjava.uh.courses.pipeline;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;

/**
 * Created by litonglinux@qq.com on 2023-08-07_16:17
 */
@Slf4j
public class CourseKapUpdatePipeline implements Pipeline {
  @Override
  public void process(ResultItems resultItems, Task task) {
    List<Map<String, Object>> dataList = resultItems.get("dataList");
    log.info("size:{}", dataList.size());
    log.info("dataList:{}", dataList);
    //更新逻辑过于复杂,搁置开发

  }
}
