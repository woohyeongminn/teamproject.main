package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;

public class AbstractFreeBoardHandler {

  List<Member> memberList;
  List<FreeBoard> freeBoardList;
  LoginHandler loginHandler;

  public AbstractFreeBoardHandler(List<Member> memberList, List<FreeBoard> freeBoardList, LoginHandler loginHandler) {
    this.freeBoardList = freeBoardList;
    this.memberList = memberList;
    this.loginHandler = loginHandler;
  }

  private FreeBoard findByTitle (String title) {
    for (FreeBoard board : freeBoardList) {
      if (board.getFreeBoardTitle().equalsIgnoreCase(title)) {
        return board;
      }
    }
    return null;
  }

}

