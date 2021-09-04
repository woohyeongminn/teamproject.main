package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Calender;

public class CalenderListHandler extends AbstractCalenderHandler {

  List<Calender> calenderList;

  public CalenderListHandler( List<Calender> calenderList) {
    super(calenderList);
  }

  public void execute() {
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
