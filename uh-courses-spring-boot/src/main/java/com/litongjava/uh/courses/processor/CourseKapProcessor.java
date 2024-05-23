package com.litongjava.uh.courses.processor;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class CourseKapProcessor implements PageProcessor {
  private Site site = Site.me().setRetryTimes(3).setSleepTime(10000);


  @Override
  public void process(Page page) {
    Elements rows = page.getHtml().getDocument().select("table.listOfClasses > tbody > tr");
    String sourceUrl = page.getUrl().toString();

    List<Map<String, Object>> dataList = new ArrayList<>();
    int rowSize = rows.size();
    log.info("row size:{}", rowSize);
    for (int i = 0; i < rowSize; i++) {
      Element row = rows.get(i);
      Elements columns = row.select("td");

      int size = columns.size(); // 正常请求下值是15
      if (size == 15) {
        i = canEnrolled(i, row, columns, sourceUrl, dataList);
      }
      if (size == 13) {
        i = canNotEnrolled(i, row, columns, sourceUrl, dataList);
      }
    }
    page.putField("dataList", dataList);
    log.info("dataList size:{}", dataList.size());

  }

  /**
   * can not be Enrolled columns is 13
   */
  private int canNotEnrolled(int i, Element row, Elements columns, String sourceUrl, List<Map<String, Object>> dataList) {
    String focusOn = columns.get(0).text().trim();
    String crn = columns.get(1).text().trim();
    String course = columns.get(2).text().trim();
    String section = columns.get(3).text().trim();
    String title = columns.get(4).text().trim();
    String credits = columns.get(5).text().trim();
    String instructor = columns.get(6).text().trim();
    String currEnrolled = columns.get(7).text().trim();
    String seatsAvail = columns.get(8).text().trim();
    String days = columns.get(9).text().trim();
    String time = columns.get(10).text().trim();
    String room = columns.get(11).text().trim();
    String dates = columns.get(12).text().trim();
    String detailsUrl = columns.get(1).select("a").attr("href").trim();

    Map<String, Object> dataMap = new HashMap<>();
    dataMap.put("focus_on", focusOn);
    dataMap.put("crn", crn);
    dataMap.put("course", course);
    dataMap.put("section", section);
    dataMap.put("title", title);
    dataMap.put("credits", credits);
    dataMap.put("instructor", instructor);
    dataMap.put("curr_enrolled", currEnrolled);
    dataMap.put("seats_avail", seatsAvail);
    dataMap.put("days", days);
    dataMap.put("time", time);
    dataMap.put("room", room);
    dataMap.put("dates", dates);
    dataMap.put("details_url", detailsUrl);
    dataMap.put("sources_url", sourceUrl);
    dataList.add(dataMap);

    Element nextRow = row.nextElementSibling();
    if (nextRow != null) {
      Elements nextRowTds = nextRow.select("td");
      if (nextRowTds.size() == 15) {
        //下一行仍然是课程数据,不是Prerequisite(s)数据
        String nextFocusOn = nextRowTds.get(0).text().trim();
        nextFocusOn = ifNull(nextFocusOn, focusOn);
        String nextCrn = nextRowTds.get(1).text().trim();
        nextCrn = ifNull(nextCrn, crn);
        String nextCourse = nextRowTds.get(2).text().trim();
        nextCourse = ifNull(nextCourse, course);
        String nextSection = nextRowTds.get(3).text().trim();
        nextSection = ifNull(nextSection, section);
        String nextTitle = nextRowTds.get(4).text().trim();
        nextTitle = ifNull(nextTitle, title);
        String nextCredits = nextRowTds.get(5).text().trim();
        nextCredits = ifNull(nextCredits, credits);
        String nextInstructor = nextRowTds.get(6).text().trim();
        nextInstructor = ifNull(nextInstructor, instructor);
        String nextCurrEnrolled = nextRowTds.get(7).text().trim();
        nextCurrEnrolled = ifNull(nextCurrEnrolled, currEnrolled);
        String nextSeatsAvail = nextRowTds.get(8).text().trim();
        nextSeatsAvail = ifNull(nextSeatsAvail, seatsAvail);
        String nextDays = nextRowTds.get(9).text().trim();
        nextDays = ifNull(nextDays, days);
        String nextTime = nextRowTds.get(10).text().trim();
        nextTime = ifNull(nextTime, time);
        String nextRoom = nextRowTds.get(11).text().trim();
        nextRoom = ifNull(nextRoom, room);
        String nextDates = nextRowTds.get(12).text().trim();
        nextDates = ifNull(nextDates, dates);

        Map<String, Object> nextRowMap = new HashMap<>();
        nextRowMap.put("focus_on", nextFocusOn);
        nextRowMap.put("crn", nextCrn);
        nextRowMap.put("course", nextCourse);
        nextRowMap.put("section", nextSection);
        nextRowMap.put("title", nextTitle);
        nextRowMap.put("credits", nextCredits);
        nextRowMap.put("instructor", nextInstructor);
        nextRowMap.put("curr_enrolled", nextCurrEnrolled);
        nextRowMap.put("seats_avail", nextSeatsAvail);
        nextRowMap.put("days", nextDays);
        nextRowMap.put("time", nextTime);
        nextRowMap.put("room", nextRoom);
        nextRowMap.put("dates", nextDates);
        nextRowMap.put("details_url", detailsUrl);
        nextRowMap.put("sources_url", sourceUrl);
        dataList.add(nextRowMap);
        i = i + 2;
      } else {
        //跳过下一行
        String html = nextRowTds.first().html();
        dataMap.put("remark", html);
        i++;
      }

    }
    return i;
  }

  /**
   * can be Enrolled columns is 15
   */
  private int canEnrolled(int i, Element row, Elements columns, String sourceUrl, List<Map<String, Object>> dataList) {
    String focusOn = columns.get(0).text().trim();
    String crn = columns.get(1).text().trim();
    String course = columns.get(2).text().trim();
    String section = columns.get(3).text().trim();
    String title = columns.get(4).text().trim();
    String credits = columns.get(5).text().trim();
    String instructor = columns.get(6).text().trim();
    String currEnrolled = columns.get(7).text().trim();
    String seatsAvail = columns.get(8).text().trim();
    String currWaitlisted = columns.get(9).text().trim();
    String waitAvail = columns.get(10).text().trim();
    String days = columns.get(11).text().trim();
    String time = columns.get(12).text().trim();
    String room = columns.get(13).text().trim();
    String detailsUrl = columns.get(1).select("a").attr("href").trim();
    String dates = columns.get(14).text().trim();

    Map<String, Object> dataMap = new HashMap<>();
    dataMap.put("focus_on", focusOn);
    dataMap.put("crn", crn);
    dataMap.put("course", course);
    dataMap.put("section", section);
    dataMap.put("title", title);
    dataMap.put("credits", credits);
    dataMap.put("instructor", instructor);
    dataMap.put("curr_enrolled", currEnrolled);
    dataMap.put("seats_avail", seatsAvail);
    dataMap.put("curr_waitlisted", currWaitlisted);
    dataMap.put("wait_avail", waitAvail);
    dataMap.put("days", days);
    dataMap.put("time", time);
    dataMap.put("room", room);
    dataMap.put("dates", dates);
    dataMap.put("details_url", detailsUrl);
    dataMap.put("sources_url", sourceUrl);
    dataList.add(dataMap);

    Element nextRow = row.nextElementSibling();
    if (nextRow != null) {
      Elements nextRowTds = nextRow.select("td");
      if (nextRowTds.size() == 15) {
        //下一行仍然是课程数据,不是Prerequisite(s)数据
        String nextFocusOn = nextRowTds.get(0).text().trim();
        nextFocusOn = ifNull(nextFocusOn, focusOn);
        String nextCrn = nextRowTds.get(1).text().trim();
        nextCrn = ifNull(nextCrn, crn);
        String nextCourse = nextRowTds.get(2).text().trim();
        nextCourse = ifNull(nextCourse, course);
        String nextSection = nextRowTds.get(3).text().trim();
        nextSection = ifNull(nextSection, section);
        String nextTitle = nextRowTds.get(4).text().trim();
        nextTitle = ifNull(nextTitle, title);
        String nextCredits = nextRowTds.get(5).text().trim();
        nextCredits = ifNull(nextCredits, credits);
        String nextInstructor = nextRowTds.get(6).text().trim();
        nextInstructor = ifNull(nextInstructor, instructor);
        String nextCurrEnrolled = nextRowTds.get(7).text().trim();
        nextCurrEnrolled = ifNull(nextCurrEnrolled, currEnrolled);
        String nextSeatsAvail = nextRowTds.get(8).text().trim();
        nextSeatsAvail = ifNull(nextSeatsAvail, seatsAvail);
        String nextCurrWaitlisted = nextRowTds.get(9).text().trim();
        nextCurrWaitlisted = ifNull(nextCurrWaitlisted, currWaitlisted);
        String nextWaitAvail = nextRowTds.get(10).text().trim();
        nextWaitAvail = ifNull(nextWaitAvail, waitAvail);
        String nextDays = nextRowTds.get(11).text().trim();
        nextDays = ifNull(nextDays, days);
        String nextTime = nextRowTds.get(12).text().trim();
        nextTime = ifNull(nextTime, time);
        String nextRoom = nextRowTds.get(13).text().trim();
        nextRoom = ifNull(nextRoom, room);
        String nextDates = nextRowTds.get(14).text().trim();
        nextDates = ifNull(nextDates, dates);

        Map<String, Object> nextRowMap = new HashMap<>();
        nextRowMap.put("focus_on", nextFocusOn);
        nextRowMap.put("crn", nextCrn);
        nextRowMap.put("course", nextCourse);
        nextRowMap.put("section", nextSection);
        nextRowMap.put("title", nextTitle);
        nextRowMap.put("credits", nextCredits);
        nextRowMap.put("instructor", nextInstructor);
        nextRowMap.put("curr_enrolled", nextCurrEnrolled);
        nextRowMap.put("seats_avail", nextSeatsAvail);
        nextRowMap.put("curr_waitlisted", nextCurrWaitlisted);
        nextRowMap.put("wait_avail", nextWaitAvail);
        nextRowMap.put("days", nextDays);
        nextRowMap.put("time", nextTime);
        nextRowMap.put("room", nextRoom);
        nextRowMap.put("dates", nextDates);
        nextRowMap.put("details_url", detailsUrl);
        nextRowMap.put("sources_url", sourceUrl);
        dataList.add(nextRowMap);
        i = i + 2;
      } else {
        //跳过下一行
        String html = nextRowTds.first().html();
        dataMap.put("remark", html);
        i++;
      }

    }
    return i;
  }

  private String ifNull(String source, String defaultValue) {
    if (StrUtil.isBlankIfStr(source)) {
      source = defaultValue;
    }
    return source;
  }

  @Override
  public Site getSite() {
    return site;
  }

}
