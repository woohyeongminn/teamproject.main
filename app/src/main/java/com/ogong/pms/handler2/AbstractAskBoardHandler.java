package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Member;

public class AbstractAskBoardHandler {

  List<AskBoard> askBoardList;
  List<Member> memberList;

  public AbstractAskBoardHandler(List<AskBoard> askBoardList, List<Member> memberList) {
    this.askBoardList = askBoardList;
    this.memberList = memberList;
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







