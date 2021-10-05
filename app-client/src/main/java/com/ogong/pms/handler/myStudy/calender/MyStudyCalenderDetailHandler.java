package com.ogong.pms.handler.myStudy.calender;

import java.util.HashMap;
import com.ogong.pms.domain.Calender;
import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class MyStudyCalenderDetailHandler implements Command {

  RequestAgent requestAgent;

  public MyStudyCalenderDetailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  private void execute(CommandRequest request) throws Exeption {
    System.out.println();
    System.out.println("▶ 일정 상세");
    System.out.println();

    int no = (int) request.getAttribute("inputNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("studyNo", null)


    int inputDay;
    String inputContent;

    Calender detailCalender = null;
    inputDay = Prompt.inputInt(" 일 : ");
    inputContent = Prompt.inputString(" 내용 : " );
    for (int i = 0; i < calenderList.size(); i++) {
      if (calenderList.get(i).getDay() == inputDay &&
          calenderList.get(i).getCalenderContent().contains(inputContent)) {
        System.out.println();
        detailCalender = calenderList.get(i);
        System.out.printf(" >> 등록일 : %d월 %d일 %s요일\n",
            calenderList.get(i).getMonth(),
            calenderList.get(i).getDay(),
            calenderList.get(i).getDayOftheWeek());
        System.out.printf(" >> 종료일 : %s\n", calenderList.get(i).getEndDay());
        System.out.printf(" >> 내  용 : %s\n", calenderList.get(i).getCalenderContent());
        System.out.printf(" >> 중요도 : %s\n", 
            calenderList.get(i).getImportanceCalender());
        System.out.println();
      }
    }
    if (detailCalender == null) {
      System.out.printf(" '%d일'에 일정이 없습니다.\n", inputDay);
      listCalender(study);
      return;
    }

    System.out.println("---------------------");
    System.out.println("1. 수정");
    System.out.println("2. 삭제");
    System.out.println("3. 취소");

    int selectNo = Prompt.inputInt("선택> ");
    System.out.println();
    switch (selectNo) {
      case 1: updateCalender(detailCalender); break;
      case 2: deleteCalender(detailCalender, study); break;
      case 3: return;
      default : return;
    }
  }

}
