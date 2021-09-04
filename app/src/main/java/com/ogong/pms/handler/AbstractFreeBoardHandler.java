package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.FreeBoard;

public abstract class AbstractFreeBoardHandler implements Command {

  List<FreeBoard> freeBoardList;

  public AbstractFreeBoardHandler(List<FreeBoard> freeBoardList) {
    this.freeBoardList = freeBoardList;
  }

  protected FreeBoard findByTitle (String title) {
    for (FreeBoard board : freeBoardList) {
      if (board.getFreeBoardTitle().equalsIgnoreCase(title)) {
        return board;
      }
    }
    return null;
  }

}

