package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.util.Prompt;

public class CalenderDeleteHandler extends AbstractCalenderHandler {

  List<Calender> calenderList;

  public CalenderDeleteHandler( List<Calender> calenderList) {
    super(calenderList);
  }

  public void execute() {
    System.out.println();
    System.out.println("▶ 일정 삭제");

    int inputDay = Prompt.inputInt("날짜 : ");
    Calender calender = findByDay(inputDay);

    if (calender == null) {
      System.out.println("해당 날짜의 일정이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오) ");
    if (input.equalsIgnoreCase("아니오") || input.length() == 0) {
      System.out.println("일정 삭제를 취소하였습니다.");
      return;
    }

    calenderList.remove(calender);

    System.out.println("일정이 삭제되었습니다.");
  }

}
