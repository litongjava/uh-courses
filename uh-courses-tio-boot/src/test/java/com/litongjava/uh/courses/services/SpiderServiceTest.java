package com.litongjava.uh.courses.services;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.litongjava.data.utils.SnowflakeIdGenerator;
import com.litongjava.jfinal.plugin.activerecord.Db;
import com.litongjava.jfinal.plugin.activerecord.DbTemplate;
import com.litongjava.jfinal.plugin.activerecord.Record;
import com.litongjava.tio.boot.tesing.TioBootTest;
import com.litongjava.uh.courses.config.TableToJsonConfig;

public class SpiderServiceTest {

  SpiderService spiderService = new SpiderService();


  @BeforeClass
  public static void before() {
    TioBootTest.before(TableToJsonConfig.class);
  }


  @Test
  public void test_insertInstitution() {
    Record record = new Record();
    record.put("id", new SnowflakeIdGenerator(0, 0).generateId());
    record.put("abb_name", "KCC");
    record.put("name", "Kapi'olani Community College");
    boolean institution = Db.save("institution", record);
    System.out.println(institution);

  }

  @Test
  public void spiderInstitutionToDb() {
    spiderService.spiderInstitutionToDb();
  }

  @Test
  public void spiderSemesterToDb() {
    spiderService.spiderSemesterToDb();
  }

  @Test
  public void spiderSubjectToDb() {
    spiderService.spiderSubjectToDb();
  }

  @Test
  public void executeSqlFromFile() {
    DbTemplate template = Db.template("test.selectOne");
    Record first = template.findFirst();
    System.out.println(first);
  }

  @Test
  public void slectForIts() {
    DbTemplate template = Db.template("subject.slectForIts");
    List<Record> records = template.find();
    System.out.println(records);
  }

  @Test
  public void spiderCourseToDbForKap() {
    String semesterName="Fall 2023";
    spiderService.spiderCourseToDbForKap(semesterName);
  }


}
