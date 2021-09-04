package com.ogong.pms.handler2;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.util.Prompt;

public class CalenderListHandler {

  List<Calender> calenderList;

  public CalenderListHandler( List<Calender> calenderList) {
    this.calenderList = calenderList;
  }

  public void list() {
    System.out.println();
    System.out.println("▶ 일정 목록");
    System.out.println();
    Calender[] cList = calenderList.toArray(new Calender[0]);

    for (Calender calender : cList) {
      System.out.printf(
          " [ %d월 %d일 %s요일 ]\n %s\n",
          calender.getMonth(), 
          calender.getDay(),
          calender.getDayOftheWeek(),
          calender.getCalenderContent());
      System.out.println();
    }
  }

  //------------------------------------------------------------------------------------------------



}
