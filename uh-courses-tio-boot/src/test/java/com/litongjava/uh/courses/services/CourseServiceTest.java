package com.litongjava.uh.courses.services;

import org.junit.BeforeClass;
import org.junit.Test;

import com.litongjava.tio.boot.tesing.TioBootTest;
import com.litongjava.uh.courses.config.TableToJsonConfig;

/**
 * Created by litonglinux@qq.com on 2023-08-15_12:15
 */
public class CourseServiceTest {


  @BeforeClass
  public static void before() {
    TioBootTest.before(TableToJsonConfig.class);
  }


  @Test
  public void removeOldCourse() {
    CourseService courseService = new CourseService();
    courseService.removeOldCourse("KAP", "Fall 2023", "LAW");
  }
}