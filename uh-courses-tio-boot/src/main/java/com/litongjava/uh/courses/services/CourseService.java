package com.litongjava.uh.courses.services;

import java.util.List;

import com.litongjava.jfinal.plugin.activerecord.Db;

/**
 * Created by litonglinux@qq.com on 2023-08-15_11:13
 */
public class CourseService {


  public void removeOldCourse(String institution, String semesterName, String subjectAbbr, Object crn) {

  }

  public void removeOldCourse(String institution, String semesterName, String subjectAbbr) {

    String selctCourseSql = "select id from course where institution=? and term=? and subject_abbr=? and deleted=0";
    String deleteCoursesql = "delete from course where institution=? and term=? and subject_abbr=? and deleted=0";
    List<Object> courseIds = Db.query(selctCourseSql, institution, semesterName, subjectAbbr);
    courseIds.forEach(System.out::println);


    //删除course_remark表
    //开启事务
    Db.tx(() -> {
      //
      Db.delete(deleteCoursesql, institution, semesterName, subjectAbbr);
      for (Object courseId : courseIds) {
        Db.deleteByIds("course_remark", "course_id", courseId);
      }

      return true;
    });
    //关闭事务
  }
}
