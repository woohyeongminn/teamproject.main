package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.util.Prompt;

public class CalenderAddHandler extends AbstractCalenderHandler {

  public CalenderAddHandler( List<Calender> calenderList) {
    super(calenderList);

    Calender testCalender = new Calender();
    testCalender.setMonth(10);
    testCalender.setDay(15);
    testCalender.setDayOftheWeek("금");
    testCalender.setCalenderContent("자격증 공부");
    testCalender.setEndDay(new Date(System.currentTimeMillis()));
    calenderList.add(testCalender);

    testCalender = new Calender();
    testCalender.setMonth(10);
    testCalender.setDay(17);
    testCalender.setDayOftheWeek("월");
    testCalender.setCalenderContent("검정고시 공부");
    testCalender.setEndDay(new Date(System.currentTimeMillis()));
    calenderList.add(testCalender);

    testCalender = new Calender();
    testCalender.setMonth(7);
    testCalender.setDay(31);
    testCalender.setDayOftheWeek("수");
    testCalender.setCalenderContent("중간고사 공부");
    testCalender.setEndDay(new Date(System.currentTimeMillis()));
    calenderList.add(testCalender);

    testCalender = new Calender();
    testCalender.setMonth(6);
    testCalender.setDay(25);
    testCalender.setDayOftheWeek("목");
    testCalender.setCalenderContent("역사 공부");
    testCalender.setEndDay(new Date(System.currentTimeMillis()));
    calenderList.add(testCalender);
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

    int day;
    while (true) {
      day = Prompt.inputInt("일(1~31) : ");
      if (month == 2) {
        if (day > 28 || day < 1) {
          System.out.println("등록할 수 없는 '일'입니다.");
          System.out.println("다시 등록해주세요.");
          continue;
        }
      } 
      else if (month < 8 || (month % 2) == 1) {
        if (day > 31 || day < 1) {
          System.out.println("등록할 수 없는 '일'입니다.");
          System.out.println("다시 등록해주세요.");
          continue;
        }
      }
      else if (month < 8 || (month % 2) == 0) {
        if (day > 31 || day < 1) {
          System.out.println("등록할 수 없는 '일'입니다.");
          System.out.println("다시 등록해주세요.");
          continue;
        }
      }
      else if (month >= 8 || (month % 2) == 1) {
        if (day > 30 || day < 1) {
          System.out.println("등록할 수 없는 '일'입니다.");
          System.out.println("다시 등록해주세요.");
          continue;
        }
      }
      else if (month >= 8 || (month % 2) == 0) {
        if (day > 31 || day < 1) {
          System.out.println("등록할 수 없는 '일'입니다.");
          System.out.println("다시 등록해주세요.");
          continue;
        }
      }
      break;
    }

    calender.setDay(day);

    System.out.println("예시> 월");
    String inputDay;
    while (true) {
      inputDay = Prompt.inputString("요일 : ");
      if (inputDay.equals("월") || inputDay.equals("화") || inputDay.equals("수") ||
          inputDay.equals("목") || inputDay.equals("금") || inputDay.equals("토") ||
          inputDay.equals("일")) {
        calender.setDayOftheWeek(inputDay);
        break;
      }
      else {
        System.out.println("등록할 수 없는 '요일'입니다.");
      }
    }

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
