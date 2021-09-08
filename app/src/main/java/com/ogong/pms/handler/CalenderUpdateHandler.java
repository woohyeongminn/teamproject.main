package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.util.Prompt;

public class CalenderUpdateHandler extends AbstractCalenderHandler {


  public CalenderUpdateHandler( List<Calender> calenderList) {
    super(calenderList);
  }

  //------------------------------------------------------------------------------------------------

  public void execute() {
    System.out.println();
    System.out.println("▶ 일정 수정");

    int inputDay = Prompt.inputInt("날짜 : ");
    Calender calender = findByDay(inputDay);

    if (calender == null) {
      System.out.println("해당 날짜의 일정이 없습니다.");
      return;
    }

    int cMonth = Prompt.inputInt("월(" + calender.getMonth()  + ")? ");
    int cDay = Prompt.inputInt("일(" + calender.getDay() + ")? ");
    String cDayOfTheWeek = Prompt.inputString("요일(" + calender.getDayOftheWeek() + ")? ");
    String cContent = Prompt.inputString("내용(" + calender.getCalenderContent() + ")? ");
    Date cEndDay = Prompt.inputDate("종료일(" + calender.getEndDay() + ")? ");

    String input = Prompt.inputString("정말 변경하시겠습니까? (네 / 아니오) ");
    if (input.equalsIgnoreCase("아니오") || input.length() == 0) {
      System.out.println("일정 변경이 취소되었습니다.");
      return;
    }

    calender.setMonth(cMonth);
    calender.setDay(cDay);
    calender.setDayOftheWeek(cDayOfTheWeek);
    calender.setCalenderContent(cContent);
    calender.setEndDay(cEndDay);

    System.out.println("일정을 변경하였습니다.");
  }

}
