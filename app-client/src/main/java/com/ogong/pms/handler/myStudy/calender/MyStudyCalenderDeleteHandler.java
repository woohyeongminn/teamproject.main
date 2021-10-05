package com.ogong.pms.handler.myStudy.calender;

import com.ogong.pms.handler.Command;
import com.ogong.pms.handler.CommandRequest;
import com.ogong.request.RequestAgent;
import com.ogong.util.Prompt;

public class MyStudyCalenderDeleteHandler implements Command {

  RequestAgent requestAgent;

  public MyStudyCalenderDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  public void execute(CommandRequest request) {
    System.out.println();
    System.out.println("▶ 일정 삭제");

    String input = Prompt.inputString(" 정말 삭제하시겠습니까? (네 / 아니오) ");
    if (!input.equalsIgnoreCase("네")) {
      System.out.println(" >> 일정 삭제를 취소하였습니다.\n");
      return;
    }
    calenderList.remove(calender);
    study.getMyStudyCalender().remove(calender);
    System.out.println(" >> 일정이 삭제되었습니다.");
    listCalender(study);
  }

}
