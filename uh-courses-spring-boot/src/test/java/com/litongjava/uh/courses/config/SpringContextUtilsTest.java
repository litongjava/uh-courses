package com.litongjava.uh.courses.config;

import com.litongjava.uh.courses.UhCoursesApp;
import com.litongjava.uh.courses.services.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by litonglinux@qq.com on 2023-08-15_11:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UhCoursesApp.class)
public class SpringContextUtilsTest {

  @Autowired
  private ApplicationContext context;

  @Test
  public void outContext() {
    System.out.println(context);
  }

  @Test
  public void getBean() {
    CourseService courseService = SpringContextUtils.getBean(CourseService.class);
    System.out.println(courseService);
  }
}