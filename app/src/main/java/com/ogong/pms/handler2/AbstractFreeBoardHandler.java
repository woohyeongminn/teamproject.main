package com.ogong.pms.handler2;

import java.util.List;
import com.ogong.pms.domain.FreeBoard;

public abstract class AbstractFreeBoardHandler {

  List<FreeBoard> freeBoardList;
  LoginHandler loginHandler;

  public AbstractFreeBoardHandler(List<FreeBoard> freeBoardList, LoginHandler loginHandler) {
    this.freeBoardList = freeBoardList;
    this.loginHandler = loginHandler;
  }

  public FreeBoard findByTitle (String title) {
    for (FreeBoard board : freeBoardList) {
      if (board.getFreeBoardTitle().equalsIgnoreCase(title)) {
        return board;
      }
    }
    return null;
  }

}

