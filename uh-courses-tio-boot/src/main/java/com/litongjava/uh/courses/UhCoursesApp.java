package com.litongjava.uh.courses;

import com.litongjava.hotswap.wrapper.tio.boot.TioApplicationWrapper;
import com.litongjava.jfinal.aop.annotation.AComponentScan;

@AComponentScan
public class UhCoursesApp {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    TioApplicationWrapper.run(UhCoursesApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "(ms)");
  }
}