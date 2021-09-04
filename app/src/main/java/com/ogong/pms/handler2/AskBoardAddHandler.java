package com.ogong.pms.handler2;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class AskBoardAddHandler extends AbstractAskBoardHandler {

  public AskBoardAddHandler(List<AskBoard> askBoardList, List<Member> memberList) {
    super(askBoardList, memberList);
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







