package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.util.Prompt;

public class CalenderAddHandler extends AbstractCalenderHandler {

  List<Calender> calenderList;

  public CalenderAddHandler( List<Calender> calenderList) {
    super(calenderList);
  }

  public void execute() {
    System.out.println();
    System.out.println("▶ 일정 등록");
    Calender calender = new Calender();
    System.out.println();
    System.out.println("시작 날짜를 입력해 주세요.");
    calender.setMonth(Prompt.inputInt("월 : "));
    calender.setDay(Prompt.inputInt("일 : "));
    calender.setDayOftheWeek(Prompt.inputString("요일 : "));
    calender.setCalenderContent(Prompt.inputString("내용 : "));
    calender.setEndDay(Prompt.inputDate("종료일 : "));

    // yyyy년도 mm월 dd일 화요일
    // 내용 : "스터디시작하기"
    // 등록하시겠습니까?

    calenderList.add(calender);
  }

  //------------------------------------------------------------------------------------------------



}
