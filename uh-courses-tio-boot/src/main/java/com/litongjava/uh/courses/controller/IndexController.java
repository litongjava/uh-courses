package com.litongjava.uh.courses.controller;

import com.litongjava.tio.http.server.annotation.RequestPath;

@RequestPath("/")
public class IndexController {
  @RequestPath("")
  public String index() {
    return "uh-course";
  }
}