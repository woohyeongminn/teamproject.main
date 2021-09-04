package com.ogong.pms.handler;

import java.sql.Date;
import java.util.List;
import com.ogong.pms.domain.FreeBoard;
import com.ogong.pms.domain.Member;
import com.ogong.util.Prompt;

public class FreeBoardAddHandler extends AbstractFreeBoardHandler {

  static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
  }

  public FreeBoardAddHandler(List<FreeBoard> freeBoardList) {
    super(freeBoardList);
  }

  @Override
  public void execute() {
    System.out.println();
    System.out.println("▶ 게시글 작성");

    FreeBoard freeBoard = new FreeBoard();

    int addCount = freeBoard.getFreeBoardNo();  //되는지 모르겠음

    freeBoard.setFreeBoardTitle(Prompt.inputString("제목 : "));
    freeBoard.setFreeBoardWriter(getLoginUser());
    freeBoard.setFreeBoardContent(Prompt.inputString("내용 : "));
    freeBoard.setFreeBoardAtcFile(Prompt.inputString("첨부파일 : "));
    freeBoard.setFreeBoardRegisteredDate(new Date(System.currentTimeMillis()));

    addCount++;

    freeBoardList.add(freeBoard);
  }
}

