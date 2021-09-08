package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.pms.domain.Study;
import com.ogong.util.Prompt;

public class MyStudyCalender {

  List<Calender> calenderList;

  public MyStudyCalender(List<Calender> calenderList) {
    this.calenderList = calenderList;

  }

  public void addCalender(Study study) {
    System.out.println();
    System.out.println("▶ 일정 등록");

    Calender calender = new Calender();

    System.out.println();
    System.out.println("시작 날짜를 입력해 주세요.");

    int month; 
    while (true) {
      month = Prompt.inputInt("월(1~12) : ");
      if (month > 12) {
        System.out.println("\n등록할 수 없는 '월'입니다.");
        System.out.println("다시 등록해주세요.");
        continue;
      }
      break;
    }
    calender.setMonth(month);

    int day;
    while (true) {
      day = Prompt.inputInt("일(1~31) : ");
      if (((month == 2) && (day > 28 || day < 1)) ||
          ((((month < 8 && (month % 2) == 1) && (day > 31 || day < 1)) ||
              ((month < 8 && (month % 2) == 0) && (day > 30 || day < 1))) ||
              (((month >= 8 && (month % 2) == 1) && (day > 30 || day < 1)) ||
                  ((month >= 8 && (month % 2) == 0) && (day > 31 || day < 1))))) {
        System.out.println("등록할 수 없는 '일'입니다.");
        System.out.println("다시 등록해주세요.");
        continue;
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
    while (true) {
      calender.setEndDay(Prompt.inputDate("종료일 : "));
      if ((calender.getEndDay().getMonth() + 1 <= month) && 
          (calender.getEndDay().getDate() < day)) {
        System.out.println("\n종료일을 다시 입력해주세요.");
        continue;
      }
      break;
    }

    String input = Prompt.inputString("\n등록하시겠습니까? (네 / 아니오)");
    if (!input.equals("네")) {
      System.out.println("등록을 취소하였습니다.");
      return;
    }
    System.out.printf("\n'%d'월에 일정이 추가 되었습니다.\n",
        calender.getMonth());

    calenderList.add(calender);
    study.setMyStudyCalender(calenderList);

  }

  public void listCalender(Study study) {
    System.out.println();
    System.out.println("▶ 일정 목록");
    System.out.println();

    System.out.println("'월'을 입력해주세요.");
    System.out.println();
    int selectMonth; 
    while (true) {
      selectMonth = Prompt.inputInt("월: ");
      if (selectMonth > 12 || selectMonth < 1) {
        System.out.println("정확한 '월'을 입력해주세요.");
        continue;
      }
      Calender month = null;
      for (Calender calender : study.getMyStudyCalender()) {
        if (selectMonth == calender.getMonth()) {
          month = calender;
          System.out.printf(
              " [ %d월 %d일 %s요일 ]\n %s\n",
              calender.getMonth(), 
              calender.getDay(),
              calender.getDayOftheWeek(),
              calender.getCalenderContent());
          System.out.println();
        }
      }

      if (month == null) {
        System.out.println();
        System.out.printf("'%d월'에 등록된 일정이 없습니다.\n", selectMonth);
        System.out.println("\n1. 날짜 재입력");
        System.out.println("2. 등록   하기");
        System.out.println("3. 취       소");
        int selectNo = Prompt.inputInt("선택> ");
        switch (selectNo) {
          case 1 : continue;
          case 2 : addCalender(study); break;
          case 3 : return;
          default : return;
        }
      }
      if (month != null) {
        System.out.println("\n1. 상세   보기");
        System.out.println("2. 등록   하기");
        System.out.println("3. 날짜 재입력");
        System.out.println("4. 취       소");
        int selectNo = Prompt.inputInt("선택> ");
        switch (selectNo) {
          case 1 : detailCalender(month); break;
          case 2 : addCalender(study); break;
          case 3 : continue;
          case 4 : return;
          default : return;
        }
      }
    }
  }

  public void detailCalender(Calender calender) {
    System.out.println();
    System.out.println("▶ 일정 상세");
    System.out.println();

    int inputDay;
    while (true) {
      inputDay = Prompt.inputInt("일: ");
      if (calender.getDay() != inputDay) {
        System.out.println();
        System.out.println("정확한 '일'을 입력해주세요.");
        continue;
      } 
      break;
    }

    System.out.println();
    System.out.printf(">> 등록일 : %d월 %d일 %s요일\n",
        calender.getMonth(), calender.getDay(), calender.getDayOftheWeek());
    System.out.printf(">> 종료일 : %s\n", calender.getEndDay());
    System.out.printf(">> 내  용 : %s\n", calender.getCalenderContent());

    System.out.println("1. 수정하기");
    System.out.println("2. 삭제하기");
    System.out.println("3. 취    소");

    int selectNo = Prompt.inputInt("선택: ");
    switch (selectNo) {
      case 1: updateCalender(calender); break;
      case 2: deleteCalender(calender); break;
      case 3: return;
      default : return;
    }
  }



  public void updateCalender(Calender calender) {
    System.out.println();
    System.out.println("▶ 일정 수정");

    int updateMonth;
    while (true) {
      updateMonth = Prompt.inputInt("월(" + calender.getMonth()  + ")? ");
      if (updateMonth > 12 || updateMonth < 1) {
        System.out.println("\n등록할 수 없는 '월'입니다.");
        System.out.println("다시 등록해주세요.");
        continue;
      }
      break;
    }

    int updateDay;
    while (true) {
      updateDay = Prompt.inputInt("일(" + calender.getDay() + ")? ");
      if (((updateMonth == 2) && (updateDay > 28 || updateDay < 1)) ||
          ((((updateMonth < 8 && (updateMonth % 2) == 1) && (updateDay > 31 || updateDay < 1)) ||
              ((updateMonth < 8 && (updateMonth % 2) == 0) && (updateDay > 30 || updateDay < 1))) ||
              (((updateMonth >= 8 && (updateMonth % 2) == 1) && (updateDay > 30 || updateDay < 1)) ||
                  ((updateMonth >= 8 && (updateMonth % 2) == 0) && (updateDay > 31 || updateDay < 1))))) {
        System.out.println("등록할 수 없는 '일'입니다.");
        System.out.println("다시 등록해주세요.");
        continue;
      } 
      break;
    }

    String updateDayOfTheWeek;
    while (true) {
      updateDayOfTheWeek = Prompt.inputString("요일(" + calender.getDayOftheWeek() + ")? ");
      if (updateDayOfTheWeek.equals("월") || updateDayOfTheWeek.equals("화") 
          ||updateDayOfTheWeek.equals("수") || updateDayOfTheWeek.equals("목") 
          || updateDayOfTheWeek.equals("금") || updateDayOfTheWeek.equals("토") ||
          updateDayOfTheWeek.equals("일")) {
        calender.setDayOftheWeek(updateDayOfTheWeek);
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
      if ((calender.getEndDay().getMonth() + 1 <= updateMonth) && 
          (calender.getEndDay().getDate() < updateDay)) {
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

    calender.setMonth(updateMonth);
    calender.setDay(updateDay);
    calender.setDayOftheWeek(updateDayOfTheWeek);
    calender.setCalenderContent(cContent);
    calender.setEndDay(cEndDay);

    System.out.println("일정을 변경하였습니다.");
  }

  public void deleteCalender(Calender calender) {
    System.out.println();
    System.out.println("▶ 일정 삭제");

    String input = Prompt.inputString("정말 삭제하시겠습니까? (네 / 아니오) ");
    if (input.equalsIgnoreCase("아니오") || input.length() == 0) {
      System.out.println("일정 삭제를 취소하였습니다.");
      return;
    }
    calenderList.remove(calender);
    System.out.println("일정이 삭제되었습니다.");
  }

}


