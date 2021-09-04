package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.FreeBoard;

public class FreeBoardListHandler extends AbstractFreeBoardHandler {

  public FreeBoardListHandler(List<FreeBoard> freeBoardList, PerLoginHandler loginHandler) {
    super(freeBoardList, loginHandler);
  }

  public void list() {
    System.out.println();
    System.out.println("▶ 게시글 목록");

    FreeBoard[] list = freeBoardList.toArray(new FreeBoard[0]);

    for (FreeBoard freeBoard : list) {
      System.out.printf(
          "(%d)\n %s\n %s\n %s\n %s\n %s\n",
          freeBoard.getFreeBoardNo(), 
          freeBoard.getFreeBoardTitle(),
          freeBoard.getFreeBoardContent(),
          freeBoard.getFreeBoardWriter().getPerNickname(),
          freeBoard.getFreeBoardViewcount(),
          freeBoard.getFreeBoardRegisteredDate()
          );
    }
  }
}

