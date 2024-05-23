package com.litongjava.uh.courses.processor;

import org.junit.BeforeClass;
import org.junit.Test;

import com.litongjava.tio.boot.tesing.TioBootTest;
import com.litongjava.uh.courses.config.TableToJsonConfig;

import lombok.extern.slf4j.Slf4j;
import us.codecraft.webmagic.Spider;

/**
 * Created by litonglinux@qq.com on 2023/6/20_22:25
 */
@Slf4j
public class CourseKapProcessorTest {

  @BeforeClass
  public static void before() {
    TioBootTest.before(TableToJsonConfig.class);
  }

  @Test
  public void testSpider() {
    log.info("start");
    String url = "https://www.sis.hawaii.edu/uhdad/avail.classes?i=KAP&t=202410&s=CULN";
    int threadNum = 1;
    Spider.create(new CourseKapProcessor())
      // url
      .addUrl(url) // Add the url you want to scrape
      //.addPipeline(new CourseKapPipeline(null))
      //
      .thread(threadNum).run();
  }

  @Test
  public void testTrim() {
    System.out.println("start");
    System.out.println(" ".trim().length());
    System.out.println("  ".trim().length());
    System.out.println("   ".trim().length());
    System.out.println("                   ".trim().length());
    System.out.println("end");
  }

  @Test
  public void stringToInt() {
    //int i = Integer.parseInt(" ");
    int i = Integer.parseInt(" ".trim());
    System.out.println(i);
  }

}