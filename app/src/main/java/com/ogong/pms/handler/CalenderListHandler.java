package com.ogong.pms.handler;

import java.util.HashMap;
import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.util.Prompt;

public class CalenderListHandler extends AbstractCalenderHandler {

  HashMap<String, Command> commandMap;

  public CalenderListHandler( List<Calender> calenderList, HashMap<String, Command> commandMap) {
    super(calenderList);
    this.commandMap = commandMap;
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 일정 목록");
    System.out.println();

    selectList();

  }

  private void selectList() {
    int selectMonth; 

    while (true) {
      System.out.println("'월'을 입력해주세요.");
      System.out.println();
      selectMonth = Prompt.inputInt("월: ");
      if (selectMonth > 12 || selectMonth < 1) {
        System.out.println("정확한 '월'을 입력해주세요.");
        continue;
      }
      Calender month = null;
      for (Calender calender : calenderList) {
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
        System.out.println("1. 날짜 재입력");
        System.out.println("2. 등록   하기");
        System.out.println("3. 취       소");
        int selectNo = Prompt.inputInt("선택> ");
        switch (selectNo) {
          case 1 : continue;
          case 2 : commandMap.get("/calender/add").execute(); break;
          case 3 : return;
          default : return;
        }
      }
      if (month != null) {
        System.out.println("1. 상세   보기");
        System.out.println("2. 등록   하기");
        System.out.println("3. 날짜 재입력");
        System.out.println("4. 취       소");
        int selectNo = Prompt.inputInt("선택> ");
        switch (selectNo) {
          case 1 : commandMap.get("/calender/detail").execute(); break;
          case 2 : commandMap.get("/calender/add").execute(); return;
          case 3 : continue;
          case 4 : return;
          default : return;
        }
      }
    }
  }





}
