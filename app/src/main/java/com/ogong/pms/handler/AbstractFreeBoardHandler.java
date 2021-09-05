package com.ogong.pms.handler;

import java.util.List;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;

public abstract class AbstractFreeBoardHandler implements Command {

  List<FreeBoard> freeBoardList;
  List<Member> memberList;

  public AbstractFreeBoardHandler(List<FreeBoard> freeBoardList, List<Member> memberList) {
    this.freeBoardList = freeBoardList;
    this.memberList = memberList;
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

