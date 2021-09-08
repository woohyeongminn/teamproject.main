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

    int inputDay;
    Calender calender;
    while (true) {
      inputDay = Prompt.inputInt("일 : ");
      calender = findByDay(inputDay);
      if (calender == null) {
        System.out.println("해당 날짜의 일정이 없습니다.");
        System.out.println("다시 입력해주세요.");
        continue;
      }
      break;
    }
    int cMonth;
    while (true) {
      cMonth = Prompt.inputInt("월(" + calender.getMonth()  + ")? ");
      if (cMonth > 12 || cMonth < 1) {
        System.out.println("\n등록할 수 없는 '월'입니다.");
        System.out.println("다시 등록해주세요.");
        continue;
      }
      break;
    }

    int cDay;
    while (true) {
      cDay = Prompt.inputInt("일(" + calender.getDay() + ")? ");
      if (((cMonth == 2) && (cDay > 28 || cDay < 1)) ||
          ((((cMonth < 8 && (cMonth % 2) == 1) && (cDay > 31 || cDay < 1)) ||
              ((cMonth < 8 && (cMonth % 2) == 0) && (cDay > 30 || cDay < 1))) ||
              (((cMonth >= 8 && (cMonth % 2) == 1) && (cDay > 30 || cDay < 1)) ||
                  ((cMonth >= 8 && (cMonth % 2) == 0) && (cDay > 31 || cDay < 1))))) {
        System.out.println("등록할 수 없는 '일'입니다.");
        System.out.println("다시 등록해주세요.");
        continue;
      } 
      break;
    }


    String cDayOfTheWeek;
    while (true) {
      cDayOfTheWeek = Prompt.inputString("요일(" + calender.getDayOftheWeek() + ")? ");
      if (cDayOfTheWeek.equals("월") || cDayOfTheWeek.equals("화") ||cDayOfTheWeek.equals("수") ||
          cDayOfTheWeek.equals("목") || cDayOfTheWeek.equals("금") || cDayOfTheWeek.equals("토") ||
          cDayOfTheWeek.equals("일")) {
        calender.setDayOftheWeek(cDayOfTheWeek);
        break;
      }
      else {
        System.out.println("등록할 수 없는 '요일'입니다.");
      }
    }   

    String cContent = Prompt.inputString("내용(" + calender.getCalenderContent() + ")? ");

    Date cEndDay; 
    while (true) {
      cEndDay =  Prompt.inputDate("종료일(" + calender.getEndDay() + ")? ");
      calender.setEndDay(Prompt.inputDate("종료일 : "));
      if ((calender.getEndDay().getMonth() + 1 <= cMonth) && 
          (calender.getEndDay().getDate() < cDay)) {
        System.out.println("\n종료일을 다시 입력해주세요.");
        continue;
      }
      break;
    }

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
