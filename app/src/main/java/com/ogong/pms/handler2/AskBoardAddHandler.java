package com.ogong.pms.handler2;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class AskBoardAddHandler {

  List<AskBoard> askBoardList;
  List<Member> memberList;

  public AskBoardAddHandler(List<AskBoard> askBoardList, List<Member> memberList) {
    this.askBoardList = askBoardList;
    this.memberList = memberList;

    AskBoard askList = new AskBoard();
    askList.setAskNo(1);
    askList.setAskTitle("문의합니다.");
    askList.setAskContent("예약 방법에 대해 알고 싶습니다.");
    askList.setAskWriter(memberList.get(0));
    askList.setAskRegisteredDate(new Date(System.currentTimeMillis()));
    askList.setAskVeiwCount(askList.getAskVeiwCount() + 1);

    askBoardList.add(askList);
  }

  public void add() {
    System.out.println();
    System.out.println("▶ 문의사항");
    System.out.println();

    AskBoard askList = new AskBoard();

    askList.setAskNo(Prompt.inputInt("번호 : "));
    askList.setAskTitle(Prompt.inputString("제목 : "));
    askList.setAskContent(Prompt.inputString("내용 : "));
    askList.setAskWriter(LoginHandler.getLoginUser());
    askList.setAskRegisteredDate(new Date(System.currentTimeMillis()));

    askBoardList.add(askList);
  }

}







