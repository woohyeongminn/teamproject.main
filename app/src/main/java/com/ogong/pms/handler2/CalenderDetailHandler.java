package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.util.Prompt;

public class CalenderDetailHandler extends AbstractCalenderHandler {

  List<Calender> calenderList;

  public CalenderDetailHandler( List<Calender> calenderList) {
    super(calenderList);
  }

  //------------------------------------------------------------------------------------------------
  public void execute() {
    System.out.println();
    System.out.println("▶ 일정 상세");
    System.out.println();
    int inputDay = Prompt.inputInt("몇일? ");

    Calender calender = findByDay(inputDay);

    if (calender == null) {
      System.out.println("해당 날짜에 일정이 없습니다.");
      return;
    }
    System.out.println();
    System.out.printf(">> 등록일 : %d월 %d일 %s요일\n",
        calender.getMonth(), calender.getDay(), calender.getDayOftheWeek());
    System.out.printf(">> 종료일 : %s\n", calender.getEndDay());
    System.out.printf(">> 내  용 : %s\n", calender.getCalenderContent());

  }

  //------------------------------------------------------------------------------------------------

}
