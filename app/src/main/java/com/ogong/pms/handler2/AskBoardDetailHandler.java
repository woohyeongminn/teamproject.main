package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class AskBoardDetailHandler {

  List<AskBoard> askBoardList;
  List<Member> memberList;

  public void detail() {
    System.out.println();
    System.out.println("▶ 문의사항 상세");
    int askNo = Prompt.inputInt("번호? ");

    AskBoard askList = findByNo(askNo);

    if (askList == null) {
      System.out.println("해당 번호의 문의글이 없습니다.");
      return;
    }

    System.out.printf(">> %s\n", askList.getAskTitle());
    System.out.printf(">> %s\n", askList.getAskContent());
    System.out.printf(">> %s\n", askList.getAskWriter().getPerNickname());
    System.out.printf(">> %s\n", askList.getAskRegisteredDate());

    askList.setAskVeiwCount(askList.getAskVeiwCount() + 1);
    System.out.printf(">> %d\n", askList.getAskVeiwCount());
  }


  private AskBoard findByNo(int askNo) {
    for (AskBoard askList : askBoardList) {
      if (askList.getAskNo() == askNo) {
        return askList;
      }
    }
    return null;
  }
}







