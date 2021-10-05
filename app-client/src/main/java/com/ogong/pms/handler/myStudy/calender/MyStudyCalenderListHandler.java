package com.ogong.pms.handler.myStudy.calender;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.ogong.pms.domain.Calender;
import com.ogong.pms.domain.Study;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class MyStudyCalenderListHandler implements Command {

  RequestAgent requestAgent;

  public MyStudyCalenderListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("▶ 일정 목록");
    System.out.println();

    Date now = new Date(System.currentTimeMillis());
    System.out.printf("      << %d월 >>\n", now.getMonth() + 1 );
    System.out.println();

    List<Calender> tempCal = new ArrayList<>();
    for (Calender calender : study.getMyStudyCalender()) {
      if (now.getMonth() + 1 == calender.getMonth()) {
        System.out.printf(
            " 중요도<%s>\n[ %d월 %d일 %s요일 ]\n %s\n",
            calender.getImportanceCalender(),
            calender.getMonth(), 
            calender.getDay(),
            calender.getDayOftheWeek(),
            calender.getCalenderContent());
        tempCal.add(calender);
        System.out.println();
      }
    }

    boolean selectBack = selectCategory(tempCal, study);
    if (!selectBack) {
      return;
    }

    int selectMonth; 
    while (true) {
      selectMonth = Prompt.inputInt(" >> 다른 '월' 일정 보기 : ");
      System.out.println();
      if (selectMonth > 12 || selectMonth < 1) {
        System.out.println(" >> 정확한 '월'을 입력해 주세요.\n");
        continue;
      }
      System.out.printf("      << %d월 >>\n", selectMonth);
      System.out.println();

      List<Calender> selecMontCal = new ArrayList<>();
      for (Calender calender : study.getMyStudyCalender()) {
        if (selectMonth == calender.getMonth()) {
          selecMontCal.add(calender);
          System.out.printf(
              " 중요도<%s> \n[ %d월 %d일 %s요일 ]\n %s\n",
              calender.getImportanceCalender(),
              calender.getMonth(), 
              calender.getDay(),
              calender.getDayOftheWeek(),
              calender.getCalenderContent());
          System.out.println();
        }
      }
      selectBack = selectCategory(selecMontCal, study);
      if (!selectBack) {
        return;
      }

    }
  }

  private boolean selectCategory(List<Calender> calenderList, Study study) {
    if (calenderList.isEmpty()) {
      System.out.println();
      System.out.println(" 등록된 일정이 없습니다.");
      System.out.println("---------------------");
      System.out.println("1. 다른 월");
      System.out.println("2. 등록");
      System.out.println("3. 취소");
      int selectNo = Prompt.inputInt("선택> ");
      System.out.println();
      switch (selectNo) {
        case 1 : return true;
        case 2 : addCalender(study); break;
        case 3 : return false;
        default : return false;
      }
    }

    if (!calenderList.isEmpty()) {
      System.out.println("---------------------");
      System.out.println("1. 상세");
      System.out.println("2. 다른 월");
      System.out.println("3. 등록");
      System.out.println("4. 취소");
      int selectNo = Prompt.inputInt("선택> ");
      System.out.println();
      switch (selectNo) {
        case 1 : detailCalender(calenderList, study); break;
        case 2 : return true;
        case 3 : addCalender(study); break;
        case 4 : return false;
        default : return false;
      }
    }
    return true;
  }

}
