package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.util.Prompt;

public class CalenderListHandler extends AbstractCalenderHandler {

  public CalenderListHandler( List<Calender> calenderList) {
    super(calenderList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 일정 목록");
    System.out.println();
    System.out.println("'월'을 입력해주세요.");
    int selectMonth = Prompt.inputInt("월: ");

    if (selectMonth > 12 || selectMonth < 1) {
      System.out.println("'일'을 정확히 입력해주세요.");
    }

    Calender month = null;
    for (Calender calender : calenderList) {
      if (selectMonth == calender.getMonth()) {
        month = calender;
      }
    }

    if (month == null) {
      System.out.println("등록된 일정이 없습니다.");
      return;
    } else {
      System.out.printf(
          " [ %d월 %d일 %s요일 ]\n %s\n",
          month.getMonth(), 
          month.getDay(),
          month.getDayOftheWeek(),
          month.getCalenderContent());
      System.out.println();
    }
  }
}
