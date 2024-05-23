package com.litongjava.uh.courses;

import com.litongjava.uh.courses.config.SpringContextUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.litongjava.hotswap.wrapper.spring.boot.SpringApplicationWrapper;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SpringContextUtils.class)
public class UhCoursesApp {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplicationWrapper.run(UhCoursesApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "(ms)");

  }
}