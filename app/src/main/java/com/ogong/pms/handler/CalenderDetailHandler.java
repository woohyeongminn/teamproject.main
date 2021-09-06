package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.util.Prompt;

public class CalenderDetailHandler extends AbstractCalenderHandler {

  List<Calender> calenderList;
  CalenderUpdateHandler calenderUpdateHandler;
  CalenderDeleteHandler calenderDeleteHandler;


  public CalenderDetailHandler( List<Calender> calenderList) {
    super(calenderList);
  }

  //------------------------------------------------------------------------------------------------
  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 일정 상세");
    System.out.println();

    int inputDay;
    Calender day = null;
    while (true) {
      inputDay = Prompt.inputInt("일: ");

      if (inputDay > 31 || inputDay < 1) {
        System.out.println();
        System.out.println("정확한 '일'을 입력해주세요.");
        continue;
      }
      for (Calender calender : calenderList) {
        if (inputDay == calender.getDay()) {
          day = calender;
          System.out.println();
          System.out.printf(">> 등록일 : %d월 %d일 %s요일\n",
              day.getMonth(), day.getDay(), day.getDayOftheWeek());
          System.out.printf(">> 제  목  : %s\n", day.getEndDay());
          System.out.printf(">> 종료일 : %s\n", day.getEndDay());
          System.out.printf(">> 내  용 : %s\n", day.getCalenderContent());
        }
      }
      if (day == null) {
        System.out.println();
        System.out.println("정확한 '일'을 입력하세요");
      }
      if (day != null) {
        System.out.println("1. 수정하기");
        System.out.println("2. 삭제하기");
        System.out.println("3. 취    소");
        int selectNo = Prompt.inputInt("선택> ");
        switch (selectNo) {
          case 1 : calenderUpdateHandler.execute(); break;
          case 2 : calenderDeleteHandler.execute(); break;
          case 3 : return;
          default : return;
        }
      }
    }





  }

}
