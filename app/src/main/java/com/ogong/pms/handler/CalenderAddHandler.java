package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.util.Prompt;

public class CalenderAddHandler extends AbstractCalenderHandler {

  public CalenderAddHandler( List<Calender> calenderList) {
    super(calenderList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 일정 등록");

    Calender calender = new Calender();

    System.out.println();
    System.out.println("시작 날짜를 입력해 주세요.");

    int month = Prompt.inputInt("월(1~12) : ");
    if (month > 12) {
      System.out.println("등록할 수 없는 '월'입니다.");
      System.out.println("다시 등록해주세요.");
      return;

    }
    calender.setMonth(month);

    int day = Prompt.inputInt("일(1~31) : ");
    calender.setDay(day);

    // 8보다 작을때 홀,짝
    if (month < 8 && (month % 2 == 1)) {            //홀수일때
      if (day > 31) {
        System.out.println("등록할 수 없는 '일'입니다.");
        System.out.println("다시 등록해주세요.");
        return;
      }
    } else if (month < 8 && (month % 2 == 0)) {    //짝수일때
      if (day > 30) {
        System.out.println("등록할 수 없는 '일'입니다.");
        System.out.println("다시 등록해주세요.");
        return;
      }
    }

    // 8보다 클때 홀,짝
    if (month >= 8 && (month % 2 == 1)) {           //홀수일때
      if (day > 30) {
        System.out.println("등록할 수 없는 '일'입니다.");
        System.out.println("다시 등록해주세요.");
        return;
      }
    } else if (month < 8 && (month % 2 == 0)) {    //짝수일때
      if (day > 31) {
        System.out.println("등록할 수 없는 '일'입니다.");
        System.out.println("다시 등록해주세요.");
        return;
      }
    }

    calender.setDayOftheWeek(Prompt.inputString("요일 : "));

    calender.setCalenderContent(Prompt.inputString("내용 : "));

    calender.setEndDay(Prompt.inputDate("종료일 : "));

    String input = Prompt.inputString("등록하시겠습니까? (네 / 아니오)");
    if (!input.equals("네")) {
      System.out.println("등록을 취소하였습니다.");
      return;
    }

    calenderList.add(calender);
  }

  //------------------------------------------------------------------------------------------------



}
