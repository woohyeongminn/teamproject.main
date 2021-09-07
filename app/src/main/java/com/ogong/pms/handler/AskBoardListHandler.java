package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.AskBoard;
import com.ogong.pms.domain.Comment;

public class AskBoardListHandler extends AbstractAskBoardHandler {

  public AskBoardListHandler(List<AskBoard> askBoardList, List<Comment> commentList) {
    super(askBoardList, commentList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 문의사항 목록");

    if (askBoardList.isEmpty()) {
      System.out.println("등록된 글이 없습니다.");
      return;
    }

    for (AskBoard askList : askBoardList) {
      System.out.printf(" \n(%d)\n 제목 : %s\n 작성자 : %s\n 작성일 : %s\n 조회수 : %d\n", 
          askList.getAskNo(), 
          askList.getAskTitle(), 
          askList.getAskWriter().getPerNickname(),
          askList.getAskRegisteredDate(),
          askList.getAskVeiwCount());
    }
  }
}






