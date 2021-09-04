package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Member;

public class AskBoardListHandler extends AbstractAskBoardHandler {

  public AskBoardListHandler(List<AskBoard> askBoardList, List<Member> memberList) {
    super(askBoardList, memberList);
  }

  public void execute() {
    System.out.println();
    System.out.println("▶ 문의사항 목록");

    for (AskBoard askList : askBoardList) {
      System.out.printf("(%d)\n 제목 : %s 작성자 : %s 등록일 : %s 조회수 : %d\n", 
          askList.getAskNo(), 
          askList.getAskTitle(), 
          askList.getAskWriter().getPerNickname(),
          askList.getAskRegisteredDate(),
          askList.getAskVeiwCount());
      System.out.println();
    }
  }

}







