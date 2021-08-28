package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.util.Prompt;

public class CalenderHandler {

  List<Calender> calenderList;

  public CalenderHandler( List<Calender> calenderList) {
    this.calenderList = calenderList;
  }

  public void add() {
    System.out.println("[캘린더 등록]");
    Calender calender = new Calender();

    calender.setMonth(Prompt.inputInt("월 :"));
    calender.setDay(Prompt.inputInt("일 :"));
    calender.setDayOftheWeek(Prompt.inputString("요일 :"));
    calender.setContents(Prompt.inputString("내용 :"));
    calender.setStart(Prompt.inputDate("시작일"));
    calender.setEnd(Prompt.inputDate("종료일"));

    calenderList.add(calender);
  }

  public void list() {

  }

}
