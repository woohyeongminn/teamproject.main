package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.util.Prompt;

public class CalenderHandler {

  List<Calender> calenderList;

  public CalenderHandler( List<Calender> calenderList) {
    this.calenderList = calenderList;
  }

  public void add() {
    System.out.println();
    System.out.println("▶ 일정 등록");
    Calender calender = new Calender();
    System.out.println();
    System.out.println("시작날짜를 입력해주세요");
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
  public void detail() {
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

  public void update() {
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

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
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

  public void delete() {
    System.out.println();
    System.out.println("▶ 일정 삭제");

    int inputDay = Prompt.inputInt("날짜? ");
    Calender calender = findByDay(inputDay);

    if (calender == null) {
      System.out.println("해당 날짜의 일정이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("일정 삭제를 취소하였습니다.");
      return;
    }

    calenderList.remove(calender);

    System.out.println("일정이 삭제되었습니다.");
  }

  private Calender findByDay(int day) {
    Calender[] arr = calenderList.toArray(new Calender[0]);
    for (Object obj : arr) {
      Calender calender = (Calender) obj;
      if (calender.getDay() == day) {
        return calender;
      }
    }
    return null;
  }


}
