package com.litongjava.uh.courses.demo;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.litongjava.uh.courses.init.TableToJsonInit;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by litonglinux@qq.com on 2023-08-15_13:54
 */
public class DbSelectTest {

  @Before
  public void before() {
    TableToJsonInit.initActiveRecord();
  }

  @Test
  public void test2() {
    Kv cond = Kv.by("i", "KAP").set("semesterName", "Fall 2023");
    SqlPara sqlPara = Db.getSqlPara("subject.slectItsByIS", cond);
    List<Record> records = Db.find(sqlPara);
    System.out.println(records.size());
  }
}
