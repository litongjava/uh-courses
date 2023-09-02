package com.litongjava.uh.courses.services;

import com.litongjava.uh.courses.init.TableToJsonInit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by litonglinux@qq.com on 2023-08-15_12:15
 */
public class CourseServiceTest {

  @Before
  public void before() {
    TableToJsonInit.initActiveRecord();
  }

  @Test
  public void removeOldCourse() {
    CourseService courseService = new CourseService();
    courseService.removeOldCourse("KAP", "Fall 2023", "LAW");
  }
}