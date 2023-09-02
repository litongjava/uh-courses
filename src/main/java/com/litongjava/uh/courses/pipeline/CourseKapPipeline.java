package com.litongjava.uh.courses.pipeline;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.litongjava.data.utils.SnowflakeIdGenerator;
import com.litongjava.uh.courses.config.SpringContextUtils;
import com.litongjava.uh.courses.services.CourseService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by litonglinux@qq.com on 2023/6/20_7:05
 */
@Slf4j
public class CourseKapPipeline implements Pipeline {

  String regex = "i=(\\w+)&t=(\\w+)&s=(\\w+)";
  private List<Record> records;
  CourseService courseService;

  public CourseKapPipeline(List<Record> records, CourseService courseService) {
    this.records = records;
    this.courseService = courseService;
  }

  @SneakyThrows
  @Override
  public void process(ResultItems resultItems, Task task) {
    List<Map<String, Object>> dataList = resultItems.get("dataList");

//    String sql = "select count(1) from course where crn=? and days=? and time=? and room=? and dates=? and deleted=0";
//    Integer integer = Db.queryInt(sql, crn, days, time, room, dates);
    //不再比较内容,直接删除多多余的数据

    Pattern pattern = Pattern.compile(regex);

    // 删除旧数据
    if (dataList.size() > 0) {
      Map<String, Object> courseMap = dataList.get(0);
      Object crn = courseMap.get("crn");
      Object sourcesUrl = courseMap.get("sources_url");
      String i = null;
      String t = null;
      String s = null;

      Matcher matcher = pattern.matcher((String) sourcesUrl);

      if (matcher.find()) {
        i = matcher.group(1);
        t = matcher.group(2);
        s = matcher.group(3);
      }

      String semesterName = getSemesterName(i, t);
      log.info("i:{},t:{},s:{},semesterName:{},crn:{}", i, t, s, semesterName, crn);
      //删除旧数据

      courseService.removeOldCourse(i, semesterName, s);
    }

    //提取数据并插入
    for (Map<String, Object> courseMap : dataList) {
      Object crn = courseMap.get("crn");
      Object sourcesUrl = courseMap.get("sources_url");
      String i = null;
      String t = null;
      String s = null;

      Matcher matcher = pattern.matcher((String) sourcesUrl);

      if (matcher.find()) {
        i = matcher.group(1);
        t = matcher.group(2);
        s = matcher.group(3);
      }


      String semesterName = getSemesterName(i, t);
      log.info("i:{},t:{},s:{},semesterName:{},crn:{}", i, t, s, semesterName, crn);

//      Object days = courseMap.get("days");
//      Object time = courseMap.get("time");
//      Object room = courseMap.get("room");
//      Object dates = courseMap.get("dates");

      long threadId = Thread.currentThread().getId();
      if (threadId > 30) {
        threadId = threadId % 30;
      }
      long courseId = new SnowflakeIdGenerator(threadId, 0).generateId();
      Object remark = courseMap.remove("remark");
      Record record = new Record();
      record.put("id", courseId);
      record.put("institution", i);
      record.put("term", semesterName);
      record.put("subject_abbr", s);
      record.setColumns(courseMap);
      boolean saveResult = Db.save("course", record);
      log.info("save {},{},{}", "course", crn, saveResult);

      if (remark != null) {
        record = new Record();
        record.put("id", courseId);
        record.put("course_id", courseId);
        record.put("remark", remark);
        saveResult = Db.save("course_remark", record);
        log.info("save {},{},{}", "course_remark", crn, saveResult);
      }

    }
  }


  private String getSemesterName(String i, String t) {
    for (Record record : records) {
      if (record.getStr("i").equals(i) && record.getStr("t").equals(t)) {
        return record.getStr("name");
      }
    }
    return null;
  }
}
