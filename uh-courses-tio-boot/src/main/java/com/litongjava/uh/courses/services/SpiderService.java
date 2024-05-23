package com.litongjava.uh.courses.services;

import java.util.List;

import com.jfinal.kit.Kv;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.jfinal.plugin.activerecord.Db;
import com.litongjava.jfinal.plugin.activerecord.Record;
import com.litongjava.jfinal.plugin.activerecord.SqlPara;
import com.litongjava.uh.courses.pipeline.CourseKapPipeline;
import com.litongjava.uh.courses.pipeline.CourseKapUpdatePipeline;
import com.litongjava.uh.courses.pipeline.InstitutionPipeline;
import com.litongjava.uh.courses.pipeline.SemesterPipeline;
import com.litongjava.uh.courses.pipeline.SubjectPipeline;
import com.litongjava.uh.courses.processor.CourseKapProcessor;
import com.litongjava.uh.courses.processor.InstitutionProcessor;
import com.litongjava.uh.courses.processor.SemesterProcessor;
import com.litongjava.uh.courses.processor.SubjectProcessor;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Spider;

@Slf4j
public class SpiderService {

  public void spiderInstitutionToDb() {
    String url = "https://www.sis.hawaii.edu/uhdad/avail.classes";
    Spider.create(new InstitutionProcessor())
      // url
      .addUrl(url) // Add the url you want to scrape
      .addPipeline(new InstitutionPipeline())
      //
      .thread(5).run();
  }

  public void spiderSemesterToDb() {
    List<Record> records = Db.find("select id,abbr_name,name from institution where deleted=0");
    String urlTempalte = "https://www.sis.hawaii.edu/uhdad/avail.classes?i=%s";
    String[] urls = new String[records.size()];
    for (int i = 0; i < records.size(); i++) {
      String url = String.format(urlTempalte, records.get(i).getStr("abbr_name"));
      urls[i] = url;
    }
//    System.out.println(urls);
//    String url = "https://www.sis.hawaii.edu/uhdad/avail.classes";
    Spider.create(new SemesterProcessor())
      // url
      .addUrl(urls) // Add the url you want to scrape
      .addPipeline(new SemesterPipeline(records))
      //
      .thread(5).run();
  }

  public void spiderSubjectToDb() {
    String sql = "select s.id,i.abbr_name,s.t from semester as s left join institution as i on s.institution_id=i.id where s.deleted=0 and i.deleted=0";
    List<Record> records = Db.find(sql);
    String urlTempalte = "https://www.sis.hawaii.edu/uhdad/avail.classes?i=%s&t=%s";
    String[] urls = new String[records.size()];
    for (int i = 0; i < records.size(); i++) {
      Record record = records.get(i);
      String abbrName = record.getStr("abbr_name");
      String t = record.getStr("t");
      String url = String.format(urlTempalte, abbrName, t);
      urls[i] = url;
    }
//    System.out.println(urls);
//    String url = "https://www.sis.hawaii.edu/uhdad/avail.classes";
    int threadNum = 5;
    Spider.create(new SubjectProcessor())
      // url
      .addUrl(urls) // Add the url you want to scrape
      .addPipeline(new SubjectPipeline(records))
      //
      .thread(threadNum).run();
  }

  /**
   * 指定 学期的课程信息
   *
   * @param semesterName semesterName,eg Fall 2023
   */
  public void spiderCourseToDbForKap(String semesterName) {
    Kv cond = Kv.by("i", "KAP").set("semesterName", "Fall 2023");
    SqlPara sqlPara = Db.getSqlPara("subject.slectItsByIS", cond);
    List<Record> records = Db.find(sqlPara);
    spiderKapSubject(records);
  }

  /**
   * 爬取Kap的指定科目的课程信息
   *
   * @param records
   */
  private void spiderKapSubject(List<Record> records) {
    String urlTempalte = "https://www.sis.hawaii.edu/uhdad/avail.classes?i=%s&t=%s&s=%s";
    String[] urls = new String[records.size()];
    for (int i = 0; i < records.size(); i++) {
      Record record = records.get(i);
      String abbrName = record.getStr("i");
      String t = record.getStr("t");
      String s = record.getStr("s");
      String url = String.format(urlTempalte, abbrName, t, s);
      urls[i] = url;
    }
//    System.out.println(Arrays.toString(urls));
    CourseService courseService = null;
    try {
      courseService = Aop.get(CourseService.class);
      log.info("courseService:{}",courseService);
    } catch (Exception e) {
      courseService = new CourseService();
    }

    //https://www.sis.hawaii.edu/uhdad/avail.classes?i=KAP&t=202410&s=ACC
    int threadNum = 1;
    Spider.create(new CourseKapProcessor())
      // url
      .addUrl(urls) // Add the url you want to scrape
      // .addUrl("https://www.sis.hawaii.edu/uhdad/avail.classes?i=KAP&t=202410&s=LAW")
      .addPipeline(new CourseKapPipeline(records, new CourseService()))
      //
      .thread(threadNum).run();
  }

  /**
   * 更新指定页面的数据
   *
   * @param url
   */
  public void updateClassesFromUrl(String url) {
    int threadNum = 1;
    Spider.create(new CourseKapProcessor())
      // url
      .addUrl(url) // Add the url you want to scrape
      // .addUrl("https://www.sis.hawaii.edu/uhdad/avail.classes?i=KAP&t=202410&s=LAW")
      .addPipeline(new CourseKapUpdatePipeline())
      //
      .thread(threadNum).run();
  }
}
