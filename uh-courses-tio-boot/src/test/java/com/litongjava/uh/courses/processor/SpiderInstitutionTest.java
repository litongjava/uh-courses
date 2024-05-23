package com.litongjava.uh.courses.processor;

import org.junit.Test;

import com.litongjava.uh.courses.pipeline.InstitutionPipeline;

import us.codecraft.webmagic.Spider;

public class SpiderInstitutionTest {

  @Test
  public void test() {
    String url = "https://www.sis.hawaii.edu/uhdad/avail.classes";
    Spider.create(new InstitutionProcessor())
        // url
        .addUrl(url) // Add the url you want to scrape
        .addPipeline(new InstitutionPipeline())
        //
        .thread(5).run();
  }

}
